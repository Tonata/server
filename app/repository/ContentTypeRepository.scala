package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection
import domain.ContentType

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

object ContentTypeRepository extends ContentTypeRepository with DataConnection {
  override lazy val tableName = "contypes"

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

