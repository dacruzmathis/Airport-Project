ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

val DoobieVersion = "1.0.0-RC1"
val NewTypeVersion = "0.4.4"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaProjet030123",
    libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "4.0.+",
    libraryDependencies += "com.h2database" % "h2" % "1.4.+",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.+",
  )