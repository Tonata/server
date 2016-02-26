package conf.connection

import java.net.InetAddress

import com.datastax.driver.core.Cluster
import com.mongodb.casbah.{MongoClient, MongoClientURI, MongoDB}
import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.KeySpace
import com.websudos.phantom.connectors.SessionProvider
import com.websudos.phantom.dsl._
import scala.collection.JavaConversions._

/**
  * Created by hashcode on 2016/02/11.
  */
object Config{
  val config = ConfigFactory.load()

}

object HashDB {
  def getConnection(): MongoDB={
    val hosts = Config.config.getString("mongodb.host")
    val database = Config.config.getString("mongodb.database")
    val uri = MongoClientURI(hosts)
    MongoClient(uri)(database)
  }
}

trait DefaultsConnector extends SessionProvider {
  val config = ConfigFactory.load()
  implicit val space: KeySpace = DataConnection.keySpace
  val cluster = DataConnection.cluster
  override implicit lazy val session: Session = DataConnection.session
}

object DataConnection {
  val config = ConfigFactory.load()
  val hosts: Seq[String] = Config.config.getStringList("cassandra.host").toList
  val inets = hosts.map(InetAddress.getByName)
  val keySpace: KeySpace = KeySpace(Config.config.getString("cassandra.keyspace"))

  val cluster =
    Cluster.builder()
      .addContactPoints(inets)
      .withClusterName(Config.config.getString("cassandra.cluster"))
      //      .withCredentials(config.getString("cassandra.username"), config.getString("cassandra.password"))
      .build()
  val session: Session = cluster.connect(keySpace.name)
}
