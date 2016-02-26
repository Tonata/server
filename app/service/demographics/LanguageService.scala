package service.demographics

import com.datastax.driver.core.ResultSet
import domain.demographics.Language
import repository.demographics.LanguageRepository
import service.Service


import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LanguageService extends Service{
  def saveOrUpdate(entity: Language): Future[ResultSet] = {
    LanguageRepository.save(entity)
  }
  def get(id:String):Future[Option[Language]] ={
    LanguageRepository.findById(id)
  }

  def getAll:Future[Seq[Language]] ={
    LanguageRepository.findAll
  }

}
