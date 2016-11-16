package repository.content

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.content.ContentType

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//name: String,
//description: String
sealed class ContentTypeRepository extends CassandraTable[ContentTypeRepository, ContentType] {
  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(row: Row): ContentType = {
    ContentType(
      id(row),
      name(row),
      description(row)
    )
  }
}

object ContentTypeRepository extends ContentTypeRepository with RootConnector {
  override lazy val tableName = "conttypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(contentType: ContentType): Future[ResultSet] = {
    insert
      .value(_.id,contentType.id)
      .value(_.name, contentType.name)
      .value(_.description, contentType.description)
      .future()
  }

  def getContentTypeById(id: String): Future[Option[ContentType]] = {
    select.where(_.id eqs id).one()
  }

  def getAllContentTypes: Future[Seq[ContentType]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}

