package repository.content

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.content.Source

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/25.
 */
sealed class SourceRepository extends CassandraTable[SourceRepository, Source] {

  object org extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object name extends StringColumn(this)

  object description extends StringColumn(this)


  override def fromRow(row: Row): Source = {
    Source(
      org(row),
      id(row),
      name(row),
      description(row)
    )
  }
}

object SourceRepository extends SourceRepository with RootConnector {
  override lazy val tableName = "sources"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(source: Source): Future[ResultSet] = {
    insert
      .value(_.org, source.org)
      .value(_.id, source.id)
      .value(_.name, source.name)
      .value(_.description, source.description)
      .future()
  }

  def getSourceById(org:String,id: String): Future[Option[Source]] = {
    select.where(_.org eqs org).and(_.id eqs id).one()
  }

  def getAllSources(org:String): Future[Seq[Source]] = {
    select.where(_.org eqs org).fetchEnumerator() run Iteratee.collect()
  }
}
