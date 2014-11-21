package com.cantorandball.owl.prototype.api

import scala.slick.jdbc.JdbcBackend.Database
import com.mchange.v2.c3p0.ComboPooledDataSource

trait H2SlickDatabase extends SlickDatabase {
  override val source = new ComboPooledDataSource
}
