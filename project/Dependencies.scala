import sbt._

object Dependencies {
  val json4sCore = "org.json4s" %% "json4s-core" % Versions.json4s
  val json4sExt = "org.json4s" %% "json4s-ext"  % Versions.json4s
  val json4sNative= "org.json4s" %% "json4s-native" % Versions.json4s
  val json4sJackson = "org.json4s" %% "json4s-jackson" % Versions.json4s
  val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalatest
  val scalaMock ="org.scalamock" %% "scalamock-scalatest-support" % Versions.scalamock
}