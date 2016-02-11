package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection
import domain.User

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */

sealed class UserRepository extends CassandraTable[UserRepository, User] {

  object id extends StringColumn(this) with PartitionKey[String]

  object username extends StringColumn(this)

  object firstName extends StringColumn(this)

  object lastName extends StringColumn(this)

  object otherName extends StringColumn(this)
  object enable extends BooleanColumn(this)

  object password extends StringColumn(this)

  object roles extends SetColumn[UserRepository, User, String](this)

  object contact extends ListColumn[UserRepository, User, String](this)

  object address extends ListColumn[UserRepository, User, String](this)



  override def fromRow(row: Row): User = {
    User(
      id(row),
      otherName(row),
      firstName(row),
      lastName(row),
      username(row),
      enable(row),
      password(row),
      roles(row),
      contact(row),
      address(row)
    )
  }
}

object UserRepository extends UserRepository with DataConnection {
  override lazy val tableName = "users"

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.id,user.id)
      .value(_.address, user.address)
      .value(_.enable, user.enable)
      .value(_.contact, user.contact)
      .value(_.firstName, user.firstName)
      .value(_.lastName, user.lastName)
      .value(_.otherName, user.otherName)
      .value(_.password, user.password)
      .value(_.roles, user.roles)
      .value(_.username, user.username)
      .future()
  }

  def getUserById(id: String): Future[Option[User]] = {
    select.where(_.id eqs id).one()
  }

  def getAllUsers: Future[Seq[User]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}

