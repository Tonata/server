package repository.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection

import scala.concurrent.Future


/**
  * Crea
  * ted by hashcode on 2016/02/21.
  */


class LocationRepository extends CassandraTable[LocationRepository,Location]{
  object company extends StringColumn(this) with PartitionKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)
  object locationTypeId extends StringColumn(this)
  object code extends StringColumn(this)

  object latitude extends StringColumn(this)
  object longitude extends StringColumn(this)
  object parentId extends StringColumn(this)

  object state extends StringColumn(this)
  object date extends DateColumn(this)

  override def fromRow(r: Row): Location = {
    Location(
      company(r),
      id(r),
      name(r),
      locationTypeId(r),
      code(r),
      latitude(r),
      longitude(r),
      parentId(r),
      state(r),
      date(r)
    )
  }
}

object LocationRepository extends LocationRepository with RootConnector {
  override lazy val tableName = "locations"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(location: Location): Future[ResultSet] = {
    insert
      .value(_.company, location.company)
      .value(_.id, location.id)
      .value(_.name, location.name)
      .value(_.locationTypeId, location.locationTypeId)
      .value(_.code, location.code)
      .value(_.latitude, location.latitude)
      .value(_.longitude, location.longitude)
      .value(_.parentId, location.parentId)
      .value(_.state, location.state)
      .value(_.date, location.date)
      .future()
  }

  def findById(company:String, id: String):Future[Option[Location]] = {
    select.where(_.company eqs company).and(_.id eqs id).one()
  }
  def findAll(company:String) : Future[Seq[Location]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
