package com.cantorandball.owl.prototype.api.video

import org.slf4j.LoggerFactory
import java.io.InputStream
import java.io.File
import java.nio.file.Files.{createTempDirectory, copy, createTempFile, readAllBytes}
import java.io.FileOutputStream
import java.nio.file.StandardCopyOption
import java.io.ByteArrayInputStream
import java.nio.file.Paths
import java.nio.file.Path

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

import scala.sys.process._
import scala.util.{Success, Failure}

trait VideosDatabase extends Videos {

  private[this] val tmp = createTempDirectory("videos-")

  override def videos = new FileSystemVideoRepository(tmp)

  private val jobs = scala.collection.mutable.Map[String, VideoJob]()

  class FileSystemVideoRepository(tmp: Path) extends VideoRepository {

    private[this] val log = LoggerFactory.getLogger(classOf[FileSystemVideoRepository])

    private val filePattern = """^audio-(.*)\.data""".r

    override def store(audio: Array[Byte], video: Array[Byte]): VideoJob = {
      val audioFile = createTempFile(tmp, "audio-", ".data")

      copy(new ByteArrayInputStream(audio), audioFile, StandardCopyOption.REPLACE_EXISTING)

      log.info("stored audio in file=[" + audioFile + "]")

      val id = audioFile.getFileName.toString match {
        case filePattern(id) => id
        case _ => throw new RuntimeException("could not match file=" + audioFile)
      }

      val videoFile = tmp.resolve("video-" + id + ".data")

      copy(new ByteArrayInputStream(video), videoFile, StandardCopyOption.REPLACE_EXISTING)

      log.info("stored video in file=[" + videoFile + "]")

      jobs(id) = Pending(id)

      log.info("started video processing job file=[" + id + "]")

      val processFile = tmp.resolve(id + ".avi")

      val f  = Future {
        val exit = Seq("ffmpeg", "-i", audioFile.toString, "-i", videoFile.toString, "-acodec", "copy", "-vcodec", "copy", processFile.toString).!
        if(exit != 0) throw new RuntimeException("failed to process video result=" + exit)
      }

      f onComplete {
        case Success(exit) => {
          log.info("video processing job successful=" + exit)
          jobs(id) = Complete(id)
        }
        case Failure(e) => {
          log.error("video processing job failed", e)
          jobs(id) = Failed(id)
        }
      }

      jobs(id)
    }

    def job(id: String): VideoJob = {
      log.info("job status=" + jobs(id))
      jobs(id)
    }

  }

}
