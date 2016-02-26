import com.typesafe.sbt.packager.archetypes.ServerLoader

name := """server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala,DebianPlugin,JavaServerAppPackaging)


scalaVersion := "2.11.7"

val PhantomVersion = "1.22.0"

maintainer := "Boniface Kabaso <boniface@kabaso.com>"
packageSummary in Linux := "Infoshare REST API"
packageDescription :=  "Infoshare Backend "
serverLoading in Debian := ServerLoader.SystemV

bashScriptExtraDefines ++= Seq(
  """addJava "-Xms1024m"""",
  """addJava "-Xmx2048m""""
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.websudos"  %% "phantom-dsl"                   % PhantomVersion,
  "org.scalatest" % "scalatest_2.11"                 % "2.2.5"
)
libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.1"
libraryDependencies += "org.springframework.security" % "spring-security-core" % "4.0.3.RELEASE"
libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.apache.tika" % "tika-core" % "1.11"
libraryDependencies += "org.mongodb" % "casbah-gridfs_2.11" % "3.1.0"

libraryDependencies += "org.apache.tika" % "tika" % "1.11"
libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core_2.11" % "2.1.1"
libraryDependencies += "org.imgscalr" % "imgscalr-lib" % "4.2"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

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

