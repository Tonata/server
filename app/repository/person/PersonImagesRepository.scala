package repository.person

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.DateColumn
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.person.PersonImages

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
class PersonImagesRepository extends CassandraTable[PersonImagesRepository, PersonImages] {


  object company extends StringColumn(this) with PartitionKey[String]

  object personId extends StringColumn(this) with PrimaryKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object url extends StringColumn(this)

  object description extends StringColumn(this)

  object size extends OptionalStringColumn(this)

  object mime extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): PersonImages = {
    PersonImages(
      company(r),
      personId(r),
      id(r),
      url(r),
      description(r),
      mime(r),
      size(r),
      date(r))
  }
}

object PersonImagesRepository extends PersonImagesRepository with RootConnector {
  override lazy val tableName = "pimages"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(image: PersonImages): Future[ResultSet] = {
    insert
      .value(_.company, image.company)
      .value(_.personId, image.personId)
      .value(_.id, image.id)
      .value(_.url, image.url)
      .value(_.size, image.size)
      .value(_.mime, image.mime)
      .value(_.description, image.description)
      .value(_.date, image.date)
      .future()
  }

  def getPersonImage(company: String, personId: String, id: String): Future[Option[PersonImages]] = {
    select.where(_.company eqs company).and(_.personId eqs personId).and(_.id eqs id).one()
  }

  def getPersonImages(company: String, personId: String): Future[Seq[PersonImages]] = {
    select.where(_.company eqs company).and(_.personId eqs personId) fetchEnumerator() run Iteratee.collect()
  }

  def getCompanyPeopleImages(company: String): Future[Seq[PersonImages]] = {
    select.where(_.company eqs company) fetchEnumerator() run Iteratee.collect()
  }
}
