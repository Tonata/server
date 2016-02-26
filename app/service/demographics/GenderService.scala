package service.demographics

import com.datastax.driver.core.ResultSet
import domain.demographics.Gender
import repository.demographics.GenderRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object GenderService extends Service{

  def saveOrUpdate(entity: Gender): Future[ResultSet] = {
    GenderRepository.save(entity)
  }

  def get(id:String):Future[Option[Gender]] ={
    GenderRepository.findById(id)
  }

  def getAll:Future[Seq[Gender]] ={
    GenderRepository.findAll
  }
}
