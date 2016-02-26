package service.demographics

import com.datastax.driver.core.ResultSet
import domain.demographics.Race
import repository.demographics.RaceRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object RaceService extends Service{

  def saveOrUpdate(entity: Race): Future[ResultSet] = {
    RaceRepository.save(entity)
  }
  def get(id:String):Future[Option[Race]] ={
    RaceRepository.findById(id)
  }

  def getAll:Future[Seq[Race]] ={
    RaceRepository.findAll
  }
}
