package com.cantorandball.owl.prototype.api

import java.nio.file.Paths

trait EnvironmentVariablesConfiguration extends Configuration {

  import scala.collection.JavaConverters._

  private[this] def environmentVariables = System.getenv.asScala.toMap

  override def videoStoragePath = environmentVariables.get("OWL_API_VIDEO_STORAGE_PATH").map(Paths.get(_))

}
