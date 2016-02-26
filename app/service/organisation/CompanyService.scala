package service.organisation

import com.datastax.driver.core.ResultSet

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object CompanyService extends Service {

  def saveOrUpdate(entity: Company): Future[ResultSet] = {
    CompanyRepository.save(entity)
  }

  def getById(id: String): Future[Option[Company]] = {
    CompanyRepository.findById(id)
  }

  def getAll:Future[Seq[Company]] ={
    CompanyRepository.findAll
  }

  def isAvailable(id: String): Future[Boolean] = {
    CompanyRepository.findById(id) map (company => {
      company match {
        case Some(_) => true
        case None => false
      }
    })
  }

}
