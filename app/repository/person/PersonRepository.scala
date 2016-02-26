package repository.person

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.person.Person

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/10/31.
 */

sealed class PersonRepository extends CassandraTable[PersonRepository, Person] {

  object org extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object firstName extends StringColumn(this)

  object emailAddress extends StringColumn(this)

  object lastName extends StringColumn(this)

  object authvalue extends StringColumn(this)

  object enabled extends BooleanColumn(this)

  object accountNonExpired extends BooleanColumn(this)

  object credentialsNonExpired extends BooleanColumn(this)

  object accountNonLocked extends BooleanColumn(this)

  object state extends StringColumn(this)

  override def fromRow(r: Row): Person = {
    Person(
      org(r),
      id(r),
      firstName(r),
      emailAddress(r),
      lastName(r),
      authvalue(r),
      enabled(r),
      accountNonExpired(r),
      credentialsNonExpired(r),
      accountNonLocked(r),
      state(r)
    )
  }
}

object PersonRepository extends PersonRepository with RootConnector {
  override lazy val tableName = "person"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(person: Person): Future[ResultSet] = {
    insert
      .value(_.org, person.org)
      .value(_.accountNonExpired, person.accountNonExpired)
      .value(_.authvalue, person.authvalue)
      .value(_.credentialsNonExpired, person.credentialsNonExpired)
      .value(_.accountNonLocked, person.accountNonLocked)
      .value(_.emailAddress, person.emailAddress)
      .value(_.enabled, person.enabled)
      .value(_.firstName, person.firstName)
      .value(_.id, person.id)
      .value(_.lastName, person.lastName)

      .value(_.state, person.state)
      .future() flatMap {
      _ => {
        UsersRepository.insert
          .value(_.org, person.org)
          .value(_.accountNonExpired, person.accountNonExpired)
          .value(_.authvalue, person.authvalue)
          .value(_.credentialsNonExpired, person.credentialsNonExpired)
          .value(_.accountNonLocked, person.accountNonLocked)
          .value(_.emailAddress, person.emailAddress)
          .value(_.enabled, person.enabled)
          .value(_.firstName, person.firstName)
          .value(_.id, person.id)
          .value(_.lastName, person.lastName)
          .value(_.state, person.state)
          .future()
      }
    }


  }

  def findPeople(org: String): Future[Seq[Person]] = {
    select.where(_.org eqs org).fetchEnumerator() run Iteratee.collect()
  }

  def findPerson(org: String, personId: String): Future[Option[Person]] = {
    select.where(_.org eqs org).and(_.id eqs personId).one()
  }

}
