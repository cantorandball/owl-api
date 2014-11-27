package com.cantorandball.owl.prototype.api.video

import com.cantorandball.owl.prototype.api.rest._
import org.scalatra._
import com.cantorandball.owl.prototype.api.Endpoint

trait VideoEndpoint extends Endpoint {
  this: ScalatraServlet with Videos =>

  post("/videos") {
    val job = videos.store(audio = decode(params("audio")), video = decode(params("video")))
    Accepted(headers = Map("Content-Location" -> (self + "/jobs" + url(job.id))))
  }

  private val dataPattern = """^data\:.*\;base64\,(.*)$""".r

  private def decode(dataUrl: String): Array[Byte] = {
    val data = dataUrl match {
      case dataPattern(data) => data
      case _ => throw new RuntimeException("could parse data file=" + dataUrl)
    }
    base64.Decode(data).right.get
  }

}
