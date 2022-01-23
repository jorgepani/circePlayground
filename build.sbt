import Dependencies._

val appName      = "circeplayground"
val appMainClass = "com.homeaway.sapling.web.Main"

lazy val commonSettings = Seq(
  organization := "com.jorgepani.techtalks"
  //assembleArtifact    := false,
  //libraryDependencies +=  scalaTest
)

lazy val `circeplayground` = (project in file("."))
  .settings(name := appName)
  .aggregate(application)

lazy val application = (project in file("application"))
  .settings(commonSettings)
  .settings(
    name := "application",
    libraryDependencies ++= Seq(Logging.slf4jApi, Logging.logBackClassic % Runtime) ++ Circe.all ++ Monocle.all ++ Seq(
      Testing.scalaTest),
    scalacOptions ++= Seq("-language:dynamics")
  )
