package com.cantorandball.owl.prototype.api.video

import com.cantorandball.owl.prototype.api.Endpoint
import org.scalatra._

trait VideoJobEndpoint extends Endpoint {
  this: ScalatraServlet with Videos =>

  get("/videos/jobs/:id") {
    videos.job(params("id")) match {
      case success: Complete   => SeeOther(root + "/videos/" + params("id"))
      case _                   => videos.job(params("id"))
    }
  }

}
