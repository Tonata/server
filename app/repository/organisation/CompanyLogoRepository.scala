package repository.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
class CompanyLogoRepository extends CassandraTable[CompanyLogoRepository, CompanyLogo] {


  object company extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object url extends StringColumn(this)

  object description extends StringColumn(this)

  object size extends OptionalStringColumn(this)

  object mime extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): CompanyLogo = {
    CompanyLogo(
      company(r),
      id(r),
      url(r),
      size(r),
      description(r),
      mime(r),
      date(r))
  }
}

object CompanyLogoRepository extends CompanyLogoRepository with RootConnector {
  override lazy val tableName = "clogos"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(dept: CompanyLogo) = {
    insert
      .value(_.company, dept.company)
      .value(_.id, dept.id)
      .value(_.url, dept.url)
      .value(_.size, dept.size)
      .value(_.mime, dept.mime)
      .value(_.date, dept.date)
      .value(_.description, dept.description)
      .future()
  }

  def findDCompanyLogo(company: String, id: String): Future[Option[CompanyLogo]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }

  def findCompanyLogos(company: String): Future[Seq[CompanyLogo]] = {
    select.where(_.company eqs company) fetchEnumerator() run Iteratee.collect()
  }
}
