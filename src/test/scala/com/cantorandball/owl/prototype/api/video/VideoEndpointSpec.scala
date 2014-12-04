package com.cantorandball.owl.prototype.api.video

import org.scalatra.test.specs2.MutableScalatraSpec
import org.scalatra.ScalatraServlet
import org.specs2.matcher.JsonMatchers
import java.net.URI
import com.cantorandball.owl.prototype.api.Configuration

class VideoEndpointSpec extends MutableScalatraSpec with JsonMatchers {

  private trait EmptyConfiguration extends Configuration {
    override val videoStoragePath = None
  }

  addServlet(new ScalatraServlet with VideoEndpoint with VideoJobEndpoint with VideosDatabase
                           with EmptyConfiguration, "/*")

  "POST to /videos" should {

    val postData = Map(("audio" -> "data:video/webm;base64,VEVTVC1BVURJTw=="), ("video" -> "data:video/webm;base64,VEVTVC1BVURJTw=="))

    "redirect to video on success" in {
      post("/videos", postData) {
        status must_== 202
        header("Content-Location") must beMatching("^http://localhost:[0-9]+/videos/jobs/.*$")
      }
    }

    "store uploaded videos" in {
      post("/videos", postData) {

        val location = new URI(header("Content-Location"))

        get(location.getPath) {
          status must_== 200
          body must not /("status" -> "pending")
        }
      }
    }
  }

}
