package service.util

import com.datastax.driver.core.ResultSet
import domain.util.Mail
import repository.util.MailRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/28.
 */
object MailService extends Service{
  def saveOrUpdate(entity: Mail): Future[ResultSet] = {
    MailRepository.save(entity)
  }
  def get(id:String):Future[Option[Mail]] ={
    MailRepository.findById(id)
  }

  def getAll:Future[Seq[Mail]] ={
    MailRepository.findAll
  }

}
