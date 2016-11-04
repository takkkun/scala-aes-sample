import sbt._
import sbt.Keys._

object AggregatesandeventsourcingBuild extends Build {
  lazy val aggregatesandeventsourcing = Project(
    id = "aggregatesandeventsourcing",
    base = file("."),
    settings = Defaults.coreDefaultSettings ++ Seq(
      name := "AggregatesAndEventSourcing",
      organization := "jp.co.reraku",
      version := "0.0.1",
      scalaVersion := "2.11.8",
      libraryDependencies ++= Seq(
        "org.postgresql" % "postgresql" % "9.4.1211"
      )
    )
  )
}
