import sbt._

lazy val jsonobjects =
  project
    .in(file("."))
    .enablePlugins(GitVersioning)
    .settings(
      settings,
      unmanagedSourceDirectories.in(Compile) := Seq.empty,
      unmanagedSourceDirectories.in(Test) := Seq.empty,
      publishArtifact := false
    )
    .aggregate(
      core,
      json4s
    )

lazy val core = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    settings,
    name := "jsonobjects-core",
    libraries.test
  )

lazy val json4s = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    settings,
    name := "jsonobjects-json4s",
    libraries.json4s,
    libraries.test
  )
  .dependsOn(core)

lazy val settings =
  commonSettings ++
    gitSettings ++
    publishSettings

lazy val commonSettings = Seq(
  scalaVersion := "2.12.2",
  crossScalaVersions := Seq(scalaVersion.value, "2.11.11"),
  scalacOptions ++= crossScalacOptions(scalaVersion.value),
  unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
  unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
  shellPrompt := ShellPrompt.buildShellPrompt
)

def crossScalacOptions(version: String) = Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-encoding", "UTF-8"
) ++ (CrossVersion.partialVersion(version) match {
  case Some((2, majorVersion)) if majorVersion >= 12 =>
    Seq("-target:jvm-1.8")
  case _ =>
    Seq("-target:jvm-1.7")
})


lazy val gitSettings = Seq(
  git.useGitDescribe := true
)

lazy val publishSettings = Seq(
  pomIncludeRepository := (_ => false),
  publishMavenStyle := true
)

lazy val libraries = new {
  val json4s = libraryDependencies ++= Seq(
    "org.json4s" %% "json4s-core" % version.json4s,
    "org.json4s" %% "json4s-native" % version.json4s,
    "org.json4s" %% "json4s-jackson" % version.json4s
  )

  val test = libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % version.scalatest,
    "org.scalamock" %% "scalamock-scalatest-support" % version.scalamock
  ).map(_ % "test")

  object version {
    val json4s = "3.5.2"
    val scalatest = "3.0.3"
    val scalamock = "3.5.0"
  }

}