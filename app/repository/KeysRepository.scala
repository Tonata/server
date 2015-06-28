package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection
import domain.Keys

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/14.
 */
sealed class KeysRepository extends CassandraTable[KeysRepository, Keys] {

  object id extends StringColumn(this) with PartitionKey[String]

  object value extends StringColumn(this)

  override def fromRow(row: Row): Keys = {
    Keys(
      id(row),
      value(row)
    )
  }
}

object KeysRepository extends KeysRepository with DataConnection {
  override lazy val tableName = "keys"

  def save(key: Keys): Future[ResultSet] = {
    insert
      .value(_.id, key.id)
      .value(_.value, key.value)
      .future()
  }
  def getKeyById(id: String): Future[Option[Keys]] = {
    select.where(_.id eqs id).one()
  }
  def getAllkeys: Future[Seq[Keys]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteKey(id:String) : Future[ResultSet]={
    delete.where(_.id eqs id).future()
  }
}