package repository.person

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.person.PersonContact

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonContactRepository extends CassandraTable[PersonContactRepository, PersonContact] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object addressTypeId extends StringColumn(this)

  object contactValue extends StringColumn(this)

  object status extends StringColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)


  override def fromRow(r: Row): PersonContact = {
    PersonContact(
      id(r),
      personId(r),
      addressTypeId(r),
      contactValue(r),
      status(r),
      date(r),
      state(r)
    )
  }
}

object PersonContactRepository extends PersonContactRepository with RootConnector {
  override lazy val tableName = "pcontacts"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personContact: PersonContact): Future[ResultSet] = {
    insert
      .value(_.personId, personContact.personId)
      .value(_.id, personContact.id)
      .value(_.addressTypeId, personContact.addressTypeId)
      .value(_.contactValue, personContact.contactValue)
      .value(_.status, personContact.status)
      .value(_.date, personContact.date)
      .value(_.state, personContact.state)
      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonContact]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonContacts(personId: String): Future[Seq[PersonContact]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }

}
