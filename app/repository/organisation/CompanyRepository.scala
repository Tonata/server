package repository.organisation

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection

import scala.concurrent.Future


/**
 * Created by hashcode on 2015/10/29.
 */



sealed class CompanyRepository extends CassandraTable[CompanyRepository, Company] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object details extends MapColumn[CompanyRepository, Company, String, String](this)

  object adminattached extends StringColumn(this)

  object date extends DateColumn(this)


  object state extends StringColumn(this)

  override def fromRow(r: Row): Company = {
    Company(
      id(r),
      name(r),
      details(r),
      adminattached(r),
      date(r),
      state(r)
    )
  }
}

object CompanyRepository extends CompanyRepository with RootConnector {
  override lazy val tableName = "company"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(company: Company) = {
    insert
      .value(_.id, company.id)
      .value(_.name, company.name)
      .value(_.details, company.details)
      .value(_.adminattached, company.adminattached)
      .value(_.date, company.date)
      .value(_.state, company.state)
      .future()
  }

  def updateCompany(company:Company):Future[ResultSet] ={
    update.where(_.id eqs company.id)
      .modify(_.name setTo  company.name)
      .and(_.details setTo company.details)
      .and(_.adminattached setTo company.adminattached)
      .and(_.date setTo company.date)
      .and(_.state setTo company.state)
      .future()
  }

  def findById(id: String) = {
    select.where(_.id eqs id).one()
  }

  def findAll: Future[Seq[Company]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }




}