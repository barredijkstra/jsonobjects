import sbt._

lazy val jsonobjects =
  project
    .in(file("."))
    .enablePlugins(GitVersioning)
    .aggregate(
      core,
      json4s
    )
    .settings(settings)
    .settings(settings)
    .settings(
      unmanagedSourceDirectories.in(Compile) := Seq.empty,
      unmanagedSourceDirectories.in(Test) := Seq.empty,
      publishArtifact := false
    )

lazy val core = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(settings)
  .settings(
    name := "jsonobjects-core",
    libraryDependencies ++= Seq(
      Dependencies.scalaTest % Test,
      Dependencies.scalaMock % Test
    )
  )

lazy val json4s = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(settings)
  .settings(
    name := "jsonobjects-json4s",
    libraryDependencies ++= Seq(
      Dependencies.json4sCore,
      Dependencies.json4sExt,
      Dependencies.json4sNative,
      Dependencies.json4sJackson,
      Dependencies.scalaTest % Test,
      Dependencies.scalaMock % Test
    )
  )
  .dependsOn(core)

lazy val settings =
  commonSettings ++
    gitSettings ++
    publishSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.2",
    crossScalaVersions := Seq(scalaVersion.value, "2.11.11", "2.13.0-M1"),
    organization := "nl.salp",
    organizationName := "Barre Dijkstra",
    startYear := Some(2017),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
    shellPrompt in ThisBuild := { state =>
      val project = Project.extract(state).currentRef.project
      s"[$project]> "
    }
  )

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

lazy val publishSettings =
  Seq(
    homepage := Some(url("https://github.com/barredijkstra/jsonobjects")),
    scmInfo := Some(ScmInfo(url("https://github.com/barredijkstra/jsonobjects"),
      "git@github.com:barredijkstra/jsonobjects")),
    developers += Developer("barredijkstra",
      "Barre Dijkstra",
      "dev@salp.nl",
      url("https://github.com/barredijkstra")),
    pomIncludeRepository := (_ => false)
  )