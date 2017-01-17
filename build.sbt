organization := "com.github.spockz"

name := "async-converters"

val scalaLibVersion = "2.12.1"

scalaVersion := scalaLibVersion

crossScalaVersions := Seq("2.11.8", scalaLibVersion)

releaseCrossBuild := true

libraryDependencies ++= Seq(
  "io.reactivex" % "rxjava" % "1.1.1" % "provided",
  "com.twitter" %% "util-core" % "6.40.0" % "provided",

  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

pomExtra in Global := {
  <url>https://github.com/spockz/async-converters</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://spockz.mit-license.org</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/spockz/async-converters</connection>
      <developerConnection>scm:git:git@github.com:spockz/async-converters</developerConnection>
      <url>github.com/spockz/async-converters</url>
    </scm>
    <developers>
      <developer>
        <id>spockz</id>
        <name>Alessandro Vermeulen</name>
        <url>http://alessandrovermeulen.me</url>
      </developer>
    </developers>
}

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)

autoAPIMappings := true

apiMappings ++= {
  // Lookup the path to jar from the classpath
  val classpath = (fullClasspath in Compile).value
  def findJar(nameBeginsWith: String): File = {
    classpath.find { attributed: Attributed[java.io.File] => (attributed.data ** s"$nameBeginsWith*.jar").get.nonEmpty }.get.data // fail hard if not found
  }
  // Define external documentation paths
  Map(
    findJar("util-core") -> url("https://twitter.github.io/util/docs/"),
    findJar("rxjava") -> url("http://reactivex.io/RxJava/javadoc/index.html")
  )
}
