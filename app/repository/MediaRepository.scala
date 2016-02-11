package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.Media

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
class MediaRepository extends CassandraTable[MediaRepository, Media] {

  object contentId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object description extends StringColumn(this)

  object url extends StringColumn(this)

  object mime extends StringColumn(this)
  object date extends DateColumn(this)

  object state extends StringColumn(this)

  override def fromRow(row: Row): Media = {
    Media(
      contentId(row),
      id(row),
      description(row),
      url(row),
      mime(row),
      date(row),
      state(row)
    )
  }
}

object MediaRepository extends MediaRepository with DataConnection {
  override lazy val tableName = "media"

  def save(media: Media): Future[ResultSet] = {
    insert
      .value(_.contentId, media.contentId)
      .value(_.id, media.id)
      .value(_.description, media.description)
      .value(_.url, media.url)
      .value(_.mime, media.mime)
      .value(_.date, media.date)
      .value(_.state, media.state)
      .future()
  }

  def getContentMediaById(contentId:String, id: String):Future[Option[Media]] = {
    select.where(_.contentId eqs contentId).and(_.id eqs id).one()
  }
  def getAllContentMedia(contentId:String):Future[Seq[Media]] = {
    select.where(_.contentId eqs contentId)fetchEnumerator() run Iteratee.collect()
  }

}

