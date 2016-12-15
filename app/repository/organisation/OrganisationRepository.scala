package repository.organisation

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.organisation.Organisation

import scala.concurrent.Future


/**
 * Created by hashcode on 2015/10/29.
 */



sealed class OrganisationRepository extends CassandraTable[OrganisationRepository, Organisation] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object details extends MapColumn[String, String](this)

  object adminattached extends StringColumn(this)

  object date extends DateColumn(this)


  object state extends StringColumn(this)

  override def fromRow(r: Row): Organisation = {
    Organisation(
      id(r),
      name(r),
      details(r),
      adminattached(r),
      date(r),
      state(r)
    )
  }
}

object OrganisationRepository extends OrganisationRepository with RootConnector {
  override lazy val tableName = "organisation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(company: Organisation) = {
    insert
      .value(_.id, company.id)
      .value(_.name, company.name)
      .value(_.details, company.details)
      .value(_.adminattached, company.adminattached)
      .value(_.date, company.date)
      .value(_.state, company.state)
      .future()
  }

  def updateCompany(company:Organisation):Future[ResultSet] ={
    insert
      .value(_.id, company.id)
      .value(_.name, company.name)
      .value(_.details, company.details)
      .value(_.adminattached, company.adminattached)
      .value(_.date, company.date)
      .value(_.state, company.state)
      .future()
  }

  def findById(id: String) = {
    select.where(_.id eqs id).one()
  }

  def findAll: Future[Seq[Organisation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }




}
