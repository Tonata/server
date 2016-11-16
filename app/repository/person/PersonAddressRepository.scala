package repository.person

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.person.PersonAddress

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonAddressRepository extends CassandraTable[PersonAddressRepository, PersonAddress] {


  object personId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object description extends StringColumn(this)

  object postalCode extends StringColumn(this)

  object addressTypeId extends StringColumn(this)

  object date extends DateColumn(this)

  object state extends StringColumn(this)



  override def fromRow(r: Row): PersonAddress = {
    PersonAddress(
      id(r),
      personId(r),
      description(r),
      postalCode(r),
      addressTypeId(r),
      date(r),
      state(r)
    )
  }
}

object PersonAddressRepository extends PersonAddressRepository with RootConnector {
  override lazy val tableName = "paddress"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(personAddress: PersonAddress): Future[ResultSet] = {
    insert
      .value(_.personId, personAddress.personId)
      .value(_.id, personAddress.id)
      .value(_.description, personAddress.description)
      .value(_.postalCode, personAddress.postalCode)
      .value(_.addressTypeId, personAddress.addressTypeId)
      .value(_.date, personAddress.date)
      .value(_.state, personAddress.state)
      .future()
  }

  def findById(personId: String, id: String): Future[Option[PersonAddress]] = {
    select.where(_.personId eqs personId).and(_.id eqs id).one()
  }

  def findPersonAddresses(personId: String): Future[Seq[PersonAddress]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }

}
