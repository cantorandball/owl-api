package com.cantorandball.owl.prototype.api.video

import java.io.InputStream

trait Videos {

  def videos: VideoRepository

  trait VideoRepository {
    def store(audio: Array[Byte], video: Array[Byte]): VideoJob
    def job(id: String): VideoJob
    def find(id: String): Array[Byte]
  }

}
