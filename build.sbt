
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "Big-Data-Processing"
  )

libraryDependencies ++= Seq(

  "org.scalatest" %% "scalatest" % "3.0.8" % Test

)