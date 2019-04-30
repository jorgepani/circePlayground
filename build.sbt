import com.homeaway.mts.VersionFile
import Dependencies._

val appName      = "circeplayground"
val appMainClass = "com.homeaway.sapling.web.Main"

lazy val commonSettings = Seq(
  organization        := "com.jorgepani.techtalks",
  assembleArtifact    := false,
  libraryDependencies +=  scalaTest
)

lazy val `circeplayground` = (project in file("."))
  .settings(name  := appName)
  .aggregate(application, dockerImage)

lazy val application = (project in file("application"))
  .settings(commonSettings)
  .settings(
    name                          := "application",
    libraryDependencies           ++= Seq(Logging.slf4jApi, Logging.logback % Runtime, scalaj) ++ Circe.all ++ Monocle.all,
    mainClass in assembly         := Some("com.jorgepani.techtalks.Circeplayground"),
    scalacOptions ++= Seq("-language:dynamics"),
    resourceGenerators in Compile += Def.task {
      val file = (resourceManaged in Compile).value / "conf" / "version.properties"
      val gitCommit = git.gitHeadCommit.value.getOrElse("Not Set")
      val buildVersion = version.value
      IO.write(file, VersionFile.versionFileContents(gitCommit, buildVersion))
      Seq(file)
    }.taskValue,
    assembleArtifact              := true
  )

lazy val dockerImage = (project in file("docker-image"))
  .enablePlugins(com.homeaway.mts.HADockerApp)
  .settings(commonSettings)
  .settings(
    name                            := s"$appName-assembly",
    haDockerAppName                 := appName,
    haDockerAppApplicationProject   := application,
    publishLocal                    := haDockerAppPublishLocal.value,
    publish                         := haDockerAppPublish.value
  )
