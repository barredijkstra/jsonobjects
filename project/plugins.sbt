// addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
// addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.9.2")
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "2.0.0")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25" // Needed by sbt-git