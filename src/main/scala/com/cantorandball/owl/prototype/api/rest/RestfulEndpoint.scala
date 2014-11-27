package com.cantorandball.owl.prototype.api.rest

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json._
import java.text.SimpleDateFormat
import java.net.URI

trait RestfulEndpoint extends JacksonJsonSupport
                              with JsonValidationErrorHandling
                              with CorsSupport {
  this: ScalatraBase =>

  protected implicit val jsonFormats: Formats = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm")
  }

  private def requestUri = new URI(request.getRequestURL.toString)

  private def selfUri = request.getHeader("X-Forwarded-Proto") match {
    case null => requestUri
    case protocol => new URI(protocol, requestUri.getUserInfo, requestUri.getHost, requestUri.getPort, requestUri.getPath, requestUri.getQuery, requestUri.getFragment)
  }

  def self = selfUri.toString

  private def rootUri = new URI(requestUri.getScheme, requestUri.getUserInfo, requestUri.getHost, requestUri.getPort, request.getContextPath, null, null)
  def root: String = rootUri.toString

  options("/*"){
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }

}
