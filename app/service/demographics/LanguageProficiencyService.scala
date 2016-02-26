package service.demographics

import com.datastax.driver.core.ResultSet
import domain.demographics.LanguageProficiency
import repository.demographics.LanguageProficiencyRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object LanguageProficiencyService extends Service{

  def saveOrUpdate(entity: LanguageProficiency): Future[ResultSet] = {
    LanguageProficiencyRepository.save(entity)
  }
  def get(id:String):Future[Option[LanguageProficiency]] ={
    LanguageProficiencyRepository.findById(id)
  }

  def getAll:Future[Seq[LanguageProficiency]] ={
    LanguageProficiencyRepository.findAll
  }
}
