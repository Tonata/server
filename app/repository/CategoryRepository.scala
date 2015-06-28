package repository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connection.DataConnection
import domain.Category

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/17.
 */
//name: String,
//description: String
sealed class CategoryRepository extends CassandraTable[CategoryRepository, Category] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this) with PartitionKey[String]

  object description extends StringColumn(this)

  override def fromRow(row: Row): Category = {
    Category(
      id(row),
      name(row),
      description(row)
    )
  }
}

object CategoryRepository extends CategoryRepository with DataConnection {
  override lazy val tableName = "category"

  def save(category: Category): Future[ResultSet] = {
    insert
      .value(_.id,category.id )
      .value(_.name, category.name)
      .value(_.description, category.description)
      .future()
  }

  def getCategoryById(id: String): Future[Option[Category]] = {
    select.where(_.id eqs id).one()
  }

  def getAllCategories: Future[Seq[Category]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}


