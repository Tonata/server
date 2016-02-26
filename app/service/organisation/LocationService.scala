package service.organisation

import com.websudos.phantom.dsl._

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/25.
  */
object LocationService extends Service{
  def save(location: Location): Future[ResultSet] = {
   LocationRepository.save(location)
  }

  def findById(company:String, id: String):Future[Option[Location]] = {
    LocationRepository.findById(company,id)
  }
  def findAll(company:String) : Future[Seq[Location]] = {
    LocationRepository.findAll(company)
  }

  def deleteById(id:String): Future[ResultSet] = {
    LocationRepository.deleteById(id)
  }

}
