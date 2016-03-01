package service.organisation

import com.datastax.driver.core.ResultSet
import domain.organisation.OrganisationLogo
import repository.organisation.OrganisationLogoRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
object OrganisationLogoServices {

  def SaveOrUpdate(organisation: OrganisationLogo): Future[ResultSet] = {
    OrganisationLogoRepository.save(organisation)
  }

  def findDCompanyLogo(organisation: String, id: String): Future[Option[OrganisationLogo]] = {
    OrganisationLogoRepository.findDCompanyLogo(organisation, id)
  }

  def findCompanyLogos(organisation: String): Future[Seq[OrganisationLogo]] = {
    OrganisationLogoRepository.findCompanyLogos(organisation)
  }

}
