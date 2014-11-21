package com.cantorandball.owl.prototype.api

import scala.slick.jdbc.JdbcBackend.Database
import javax.sql.DataSource

trait SlickDatabase {

  val source: DataSource
  protected lazy val db = Database.forDataSource(source)

}
