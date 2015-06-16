name := """server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

libraryDependencies += "com.websudos" % "phantom-dsl_2.11" % "1.8.12"

libraryDependencies += "com.websudos" % "phantom-connectors_2.11" % "1.8.12"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.6"

libraryDependencies += "com.github.nscala-time" % "nscala-time_2.11" % "2.0.0"

libraryDependencies += "org.scala-lang.modules" % "scala-async_2.10" % "0.9.2"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4"

libraryDependencies += "com.auth0" % "java-jwt" % "2.1.0"

libraryDependencies += "io.really" % "jwt-scala_2.11" % "1.2.2"

libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.1"

libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"




