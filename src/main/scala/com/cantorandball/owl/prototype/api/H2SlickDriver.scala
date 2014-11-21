package com.cantorandball.owl.prototype.api

trait H2SlickDriver extends SlickDriver {
  this: H2SlickDatabase =>

  override val driver = scala.slick.driver.H2Driver

}
