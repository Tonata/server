package repository.person

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.person.PersonLanguage

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonLanguageRepository extends CassandraTable[PersonLanguageRepository, PersonLanguage] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object languageId extends StringColumn(this)

  object reading extends StringColumn(this)

  object writing extends StringColumn(this)

  object speaking extends StringColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonLanguage = {
    PersonLanguage(
      id(r),
      personId(r),
      languageId(r),
      reading(r),
      writing(r),
      speaking(r),
      date(r),
      state(r)
    )
  }
}

object PersonLanguageRepository extends PersonLanguageRepository with RootConnector {
  override lazy val tableName = "plang"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personLanguage: PersonLanguage): Future[ResultSet] = {
    insert
      .value(_.personId, personLanguage.personId)
      .value(_.id, personLanguage.id)
      .value(_.languageId, personLanguage.languageId)
      .value(_.reading, personLanguage.reading)
      .value(_.speaking, personLanguage.speaking)
      .value(_.writing, personLanguage.writing)
      .value(_.date, personLanguage.date)
      .value(_.state, personLanguage.state)
      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonLanguage]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonLanguages(personId: String): Future[Seq[PersonLanguage]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }


}
