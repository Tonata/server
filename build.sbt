import com.typesafe.sbt.packager.archetypes.ServerLoader
import play.sbt.PlayLogback

name := """server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, DebianPlugin, DockerPlugin,JavaServerAppPackaging)
  .disablePlugins(PlayLogback)



scalaVersion := "2.11.8"

val PhantomVersion = "1.28.13"

maintainer := "Boniface Kabaso <boniface@kabaso.com>"
packageSummary in Linux := "Margin Mentor REST API"
packageDescription := "MarginMentorAPI Backend "
serverLoading in Debian := ServerLoader.Systemd

bashScriptExtraDefines ++= Seq(
  """addJava "-Xms1024m"""",
  """addJava "-Xmx2048m""""
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.websudos" % "phantom-dsl_2.11" % PhantomVersion,
  "com.websudos" % "phantom-reactivestreams_2.11" % PhantomVersion,
  "com.chuusai" %% "shapeless" % "2.2.5"
)
// https://mvnrepository.com/artifact/io.getclump/clump_2.11
libraryDependencies += "io.getclump" % "clump_2.11" % "0.0.11"
libraryDependencies += "org.springframework.security" % "spring-security-core" % "4.0.3.RELEASE"
libraryDependencies += "org.apache.tika" % "tika-core" % "1.11"
libraryDependencies += "org.mongodb" % "casbah-gridfs_2.11" % "3.1.0"
libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"
libraryDependencies += "org.apache.tika" % "tika" % "1.11"
libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core_2.11" % "2.1.1"
libraryDependencies += "org.imgscalr" % "imgscalr-lib" % "4.2"
libraryDependencies += "io.monix" %% "monix" % "2.0.0"
libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.11" % "0.4.3"
libraryDependencies += "me.lessis" % "base64_2.11" % "0.2.0"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "com.github.t3hnar" % "scala-bcrypt_2.11" % "2.5"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.6"
// https://mvnrepository.com/artifact/com.google.guava/guava
libraryDependencies += "com.google.guava" % "guava" % "19.0"
// https://mvnrepository.com/artifact/org.apache.commons/commons-email
libraryDependencies += "org.apache.commons" % "commons-email" % "1.4"
// https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.4.1"
libraryDependencies += "org.typelevel" %% "cats" % "0.8.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.6"
// redis-server cache
libraryDependencies += "com.github.karelcemus" %% "play-redis" % "1.2.0"
libraryDependencies += "com.github.romix.akka" %% "akka-kryo-serialization" % "0.4.1"
// https://mvnrepository.com/artifact/com.esotericsoftware.kryo/kryo
libraryDependencies += "com.esotericsoftware.kryo" % "kryo" % "2.24.0"
// https://mvnrepository.com/artifact/com.github.nscala-time/nscala-time_2.11
libraryDependencies += "com.github.nscala-time" % "nscala-time_2.11" % "2.12.0"
libraryDependencies += "com.typesafe.play" % "play-logback_2.11" % "2.5.1"




routesGenerator := InjectedRoutesGenerator
libraryDependencies += filters

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "softprops-maven" at "http://dl.bintray.com/content/softprops/maven",
  "Brando Repository" at "http://chrisdinn.github.io/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos releases" at "https://dl.bintray.com/websudos/oss-releases/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
