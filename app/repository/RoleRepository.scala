package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection
import domain.Role


import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//roleName: String,
//description: String
sealed class RoleRepository extends CassandraTable[RoleRepository, Role] {

  object id extends StringColumn(this) with PartitionKey[String]

  object roleName extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(row: Row): Role = {
    Role(
      id(row),
      roleName(row),
      description(row)
    )
  }
}

object RoleRepository extends RoleRepository with DataConnection {
  override lazy val tableName = "roles"

  def save(role: Role): Future[ResultSet] = {
    insert
      .value(_.id,role.id)
      .value(_.roleName, role.roleName)
      .value(_.description, role.description)
      .future()
  }

  def getRoleById(id: String): Future[Option[Role]] = {
    select.where(_.id eqs id).one()
  }

  def getRoles: Future[Seq[Role]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}

