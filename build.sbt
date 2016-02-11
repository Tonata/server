name := """server"""

version := "1.0-SNAPSHOT"

offline := true

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

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

libraryDependencies += "com.websudos" % "phantom-dsl_2.11" % "1.6.0"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.6"

libraryDependencies += "com.github.nscala-time" % "nscala-time_2.11" % "2.0.0"

libraryDependencies += "org.scala-lang.modules" % "scala-async_2.10" % "0.9.2"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4"

libraryDependencies += "com.auth0" % "java-jwt" % "2.1.0"

libraryDependencies += "io.really" % "jwt-scala_2.11" % "1.2.2"

libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.1"

libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"

libraryDependencies += "com.google.code.gson" % "gson" % "2.3.1"

libraryDependencies += "org.mongodb" % "casbah-gridfs_2.11" % "3.1.0"

libraryDependencies += "junit" % "junit" % "4.12"

libraryDependencies += "org.mongodb" % "casbah_2.11" % "3.1.0"

libraryDependencies += "org.apache.tika" % "tika-core" % "1.11"

libraryDependencies += "org.apache.tika" % "tika" % "1.11"

libraryDependencies += "org.imgscalr" % "imgscalr-lib" % "4.2"

libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core_2.11" % "2.1.1"

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases"                at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots"               at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging"                 at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
  "Twitter Repository"               at "http://maven.twttr.com",
  "Websudos releases"                at "https://dl.bintray.com/websudos/oss-releases/"
)




