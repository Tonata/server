package service.location

import com.datastax.driver.core.ResultSet
import domain.location.LocationType
import repository.location.LocationTypeRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LocationTypeService extends Service{
  def saveOrUpdate(entity: LocationType): Future[ResultSet] = {
    LocationTypeRepository.save(entity)
  }
  def get(id:String):Future[Option[LocationType]] ={
    LocationTypeRepository.findById(id)
  }

  def getAll:Future[Seq[LocationType]] ={
    LocationTypeRepository.findAll
  }

}
