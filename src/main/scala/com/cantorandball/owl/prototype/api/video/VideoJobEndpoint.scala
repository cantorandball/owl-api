package com.cantorandball.owl.prototype.api.video

import com.cantorandball.owl.prototype.api.Endpoint
import org.scalatra._

trait VideoJobEndpoint extends Endpoint {
  this: ScalatraServlet with Videos =>

  get("/videos/jobs/:id") {
    contentType = formats("json")
    videos.job(params("id")) match {
      case success: Complete   => SeeOther("")
      case _                   => videos.job(params("id"))
    }
  }

}
