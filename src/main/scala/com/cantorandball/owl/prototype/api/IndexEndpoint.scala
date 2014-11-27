package com.cantorandball.owl.prototype.api

import org.scalatra._
import com.cantorandball.owl.prototype.api.rest._

trait IndexEndpoint extends Endpoint {
  this: ScalatraServlet =>

  case class Index (
    name: String
  ) extends Representation

  get("/") {
    Resource(
      content = Index(
          name = "owl-api"
      ),
      links = Map(
        "self" -> self,
        "video" -> (self + "videos")
      )
    )
  }

}
