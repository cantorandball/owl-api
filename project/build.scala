import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import org.scalatra.sbt.DistPlugin._
import org.scalatra.sbt.DistPlugin.DistKeys._

object MeasurementsApiBuild extends Build {
  val Organization = "Cantor & Ball"
  val Name = "owl-api"
  val Version = "1.0.0-SNAPSHOT"
  val ScalaVersion = "2.11.1"
  val ScalatraVersion = "2.3.0"

  lazy val project = Project (
    "owl-api",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ DistPlugin.distSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,

      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.scalatra" %% "scalatra-auth" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "me.lessis" %% "base64" % "0.1.1" % "test",
        "com.typesafe.slick" %% "slick" % "2.1.0",
        "com.h2database" % "h2" % "1.4.181",
        "c3p0" % "c3p0" % "0.9.1.2",
        "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
        "org.flywaydb" % "flyway-core" % "3.0",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.json4s" %% "json4s-jackson" % "3.2.10",
        "org.mindrot" % "jbcrypt" % "0.3m",
        "me.lessis" %% "base64" % "0.1.1",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.3.v20140225" % "compile;container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.3.v20140225" % "compile;container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      )
    )
  )
}
