package com.cantorandball.owl.prototype.api

import scala.slick.driver.JdbcProfile

trait SlickDriver {

  val driver: JdbcProfile

}
