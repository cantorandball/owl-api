package com.cantorandball.owl.prototype.api

import java.nio.file.Path

trait Configuration {

  def videoStoragePath: Option[Path]

}
