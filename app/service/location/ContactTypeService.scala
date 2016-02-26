package service.location

import com.datastax.driver.core.ResultSet
import domain.location.ContactType
import repository.location.ContactTypeRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object ContactTypeService extends Service {

  def saveOrUpdate(entity: ContactType): Future[ResultSet] = {
    ContactTypeRepository.save(entity)
  }
  def get(id:String):Future[Option[ContactType]] ={
    ContactTypeRepository.findById(id)
  }

  def getAll:Future[Seq[ContactType]] ={
    ContactTypeRepository.findAll
  }
}
