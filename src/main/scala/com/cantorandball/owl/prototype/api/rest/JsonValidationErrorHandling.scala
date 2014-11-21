package com.cantorandball.owl.prototype.api.rest

import org.scalatra._
import org.json4s.`package`.MappingException
import com.fasterxml.jackson.core.JsonParseException
import org.slf4j.LoggerFactory

protected trait JsonValidationErrorHandling {
  this: ScalatraBase =>

  private val log =  LoggerFactory.getLogger(getClass)

  error {
    case e @ (_:MappingException | _:JsonParseException) => {
      log.warn("parse exception from client", e)
      BadRequest(e.getMessage())
    }
  }

}
