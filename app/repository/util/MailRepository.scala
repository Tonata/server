package repository.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.util.Mail

import scala.concurrent.Future

/**
  * Created by hashcode on 2015/11/28.
  */


class MailRepository extends CassandraTable[MailRepository, Mail] {

  object orgId extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object key extends StringColumn(this)

  object value extends StringColumn(this)

  object host extends StringColumn(this)

  object port extends StringColumn(this)

  object state extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): Mail = {
    Mail(orgId(r), id(r), key(r), value(r), host(r), port(r), state(r), date(r))
  }
}

object MailRepository extends MailRepository with RootConnector {
  override lazy val tableName = "mail"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(mail: Mail): Future[ResultSet] = {
    insert
      .value(_.orgId, mail.orgId)
      .value(_.id, mail.id)
      .value(_.key, mail.key)
      .value(_.value, mail.value)
      .value(_.host, mail.host)
      .value(_.port, mail.port)
      .value(_.state, mail.state)
      .value(_.date, mail.date)
      .future()
  }

  def findById(orgId:String, id: String): Future[Option[Mail]] = {
    select.where(_.orgId eqs orgId).and(_.id eqs id).one()
  }

  def findAll(orgId:String): Future[Seq[Mail]] = {
    select.where(_.orgId eqs orgId).fetchEnumerator() run Iteratee.collect()
  }
}

