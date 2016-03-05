organization := "io.async-converters"

name := "rxjava-twitter-util"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "io.reactivex" % "rxjava" % "1.1.1",
  "com.twitter" %% "util-core" % "6.32.0",

  "org.scalatest" %% "scalatest" % "2.2.4"
)
