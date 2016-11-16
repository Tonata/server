package repository.person

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.person.PersonRole

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/10/31.
 */
//personId: String,
//roleId: String
sealed class PersonRoleRepository extends CassandraTable[PersonRoleRepository, PersonRole] {

  object personId extends StringColumn(this) with PartitionKey[String]

  object roleId extends StringColumn(this) with PrimaryKey[String]
  object state extends StringColumn(this)

  override def fromRow(r: Row): PersonRole = {
    PersonRole(
      personId(r), roleId(r),state(r)
    )
  }
}

object PersonRoleRepository extends PersonRoleRepository with RootConnector {
  override lazy val tableName = "proles"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(role: PersonRole): Future[ResultSet] = {
    insert
      .value(_.personId, role.personId)
      .value(_.roleId, role.roleId)
      .value(_.state, role.state)
      .future()
  }

  def findRolesById(personId: String): Future[Seq[PersonRole]] = {
    select.where(_.personId eqs personId).fetchEnumerator() run Iteratee.collect()
  }
  def findRole(id: String,roleId:String): Future[Option[PersonRole]] = {
    select.where(_.personId eqs id).and(_.roleId eqs roleId).one
  }

  def deleteById(id: String,roleId:String): Future[ResultSet] = {
    delete.where(_.personId eqs id).and(_.roleId eqs roleId).future()
  }
}
