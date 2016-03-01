package service.organisation

import com.datastax.driver.core.ResultSet
import domain.organisation.Organisation
import repository.organisation.OrganisationRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object OrganisationService extends Service {

  def saveOrUpdate(entity: Organisation): Future[ResultSet] = {
    OrganisationRepository.save(entity)
  }

  def getById(id: String): Future[Option[Organisation]] = {
    OrganisationRepository.findById(id)
  }

  def getAll:Future[Seq[Organisation]] ={
    OrganisationRepository.findAll
  }

  def isAvailable(id: String): Future[Boolean] = {
    OrganisationRepository.findById(id) map (entity => {
      entity match {
        case Some(_) => true
        case None => false
      }
    })
  }

}
