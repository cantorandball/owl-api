package com.cantorandball.owl.prototype.api

import org.slf4j.LoggerFactory
import org.scalatra._
import com.cantorandball.owl.prototype.api.rest._

trait IndexEndpoint extends RestfulEndpoint {
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
        "self" -> self
      )
    )
  }

}
