package com.cantorandball.owl.prototype.api.video

abstract class VideoJob() {
  def id: String
  def status: String
}

case class Pending(val id: String, val status: String = "pending") extends VideoJob

case class Complete(val id: String, val status: String = "success") extends VideoJob

case class Failed(val id: String, val status: String = "failure") extends VideoJob
