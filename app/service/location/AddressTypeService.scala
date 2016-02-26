package service.location

import com.datastax.driver.core.ResultSet
import domain.location.AddressType
import repository.location.AddressTypeRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object AddressTypeService extends Service{
  def saveOrUpdate(entity: AddressType): Future[ResultSet] = {
    AddressTypeRepository.save(entity)
  }
  def get(id:String):Future[Option[AddressType]] ={
    AddressTypeRepository.findById(id)
  }

  def getAll:Future[Seq[AddressType]] ={
    AddressTypeRepository.findAll
  }

}
