package com.cantorandball.owl.prototype.api

import org.scalatra.ScalatraBase
import org.slf4j.LoggerFactory

trait ErrorLogging {
  this: ScalatraBase =>

  private[this] val log = LoggerFactory.getLogger(classOf[ErrorLogging])

  error {
    case e: Throwable => {
      log.error("request failed:", e)
      throw e
    }
  }

}
