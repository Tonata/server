package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.PublishedContent

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
class PublishedContentRepository extends CassandraTable[EditedContentRepository, PublishedContent] {


  object id extends StringColumn(this) with PartitionKey[String]

  object dateCreated extends DateColumn(this)

  object creator extends StringColumn(this)

  object source extends StringColumn(this)

  object category extends StringColumn(this)

  object title extends StringColumn(this)

  object content extends StringColumn(this)

  object contentType extends StringColumn(this)

  object status extends StringColumn(this)

  object state extends StringColumn(this)

  override def fromRow(row: Row): PublishedContent = {
    PublishedContent(
      id(row),
      dateCreated(row),
      creator(row),
      source(row),
      category(row),
      title(row),
      content(row),
      contentType(row),
      status(row),
      state(row)
    )
  }
}

object PublishedContentRepository extends PublishedContentRepository with DataConnection {
  override lazy val tableName = "pcontent"

  def save(content: PublishedContent): Future[ResultSet] = {
    insert
      .value(_.id, content.id)
      .value(_.dateCreated, content.dateCreated)
      .value(_.creator, content.creator)
      .value(_.source, content.source)
      .value(_.category, content.category)
      .value(_.title, content.title)
      .value(_.content, content.content)
      .value(_.contentType, content.contentType)
      .value(_.status, content.status)
      .value(_.state, content.state)
      .future()
  }

  def getContentById(id: String): Future[Option[PublishedContent]] = {
    select.where(_.id eqs id).one()
  }

  def getAllContents: Future[Seq[PublishedContent]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getContents(startValue: Int): Future[Iterator[PublishedContent]] = {
    select.fetchEnumerator() run Iteratee.slice(startValue, 20)
  }
}

