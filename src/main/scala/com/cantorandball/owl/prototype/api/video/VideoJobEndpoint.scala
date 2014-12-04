package com.cantorandball.owl.prototype.api.video

import com.cantorandball.owl.prototype.api.Endpoint
import org.scalatra._
import org.json4s.jackson.Serialization.write

trait VideoJobEndpoint extends Endpoint {
  this: ScalatraServlet with Videos =>

  get("/videos/jobs/:id") {
    videos.job(params("id")) match {
      case success: Complete   => {
        val uri = root + "/videos/" + params("id")
        response.setStatus(303)
        response.addHeader("Location", uri)
        contentType = "text/plain"
        uri
      }
      case _                   => videos.job(params("id"))
    }
  }

}
