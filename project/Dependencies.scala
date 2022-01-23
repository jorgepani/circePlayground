import sbt._

object Dependencies {
  object Logging {
    val slf4jApi       = "org.slf4j"      % "slf4j-api"       % "1.7.30"
    val logBackClassic = "ch.qos.logback" % "logback-classic" % "1.2.3"
  }

  object Circe {
    val circeVersion       = "0.14.1"
    val circeCore          = "io.circe" %% "circe-core" % circeVersion
    val circeGeneric       = "io.circe" %% "circe-generic" % circeVersion
    val circeGenericExtras = "io.circe" %% "circe-generic-extras" % circeVersion
    val circeJava8         = "io.circe" %% "circe-java8" % circeVersion
    val circeJawn          = "io.circe" %% "circe-jawn" % circeVersion
    val circeParser        = "io.circe" %% "circe-parser" % circeVersion
    val circeOptics        = "io.circe" %% "circe-optics" % circeVersion

    val all = List(circeCore, circeGeneric, circeGenericExtras, circeParser, circeOptics)
  }

  object Monocle {
    val monocleVersion = "2.1.0"
    val core           = "com.github.julien-truffaut" %% "monocle-core" % monocleVersion
    val generic        = "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion
    val macros         = "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion

    val all = List(core, generic, macros)

  }

  object Testing {
    val scalaTest = "org.scalatest" %% "scalatest" % "3.1.4"
  }

}
