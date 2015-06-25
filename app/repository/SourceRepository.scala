package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl.{StringColumn, _}
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.Source

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/25.
 */
class SourceRepository extends CassandraTable[SourceRepository, Source] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(row: Row): Source = {
    Source(
      id(row),
      name(row),
      description(row)
    )
  }
}

object SourceRepository extends SourceRepository with DataConnection {
  override lazy val tableName = "sources"

  def save(source: Source): Future[ResultSet] = {
    insert
      .value(_.id, source.id)
      .value(_.name, source.name)
      .value(_.description, source.description)
      .future()
  }

  def getSourceById(id: String): Future[Option[Source]] = {
    select.where(_.id eqs id).one()
  }

  def getAllSources: Future[Seq[Source]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}
