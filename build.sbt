name := """nhs-roll-call-backend"""
organization := "com.ukvscovid19"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.11"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.12"
libraryDependencies += "io.swagger" %% "swagger-play2" % "1.7.1"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ukvscovid19.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ukvscovid19.binders._"
