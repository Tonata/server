package service.organisation

import com.datastax.driver.core.ResultSet
import repository.organisation.OrganisationLogoRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
object CompanyLogoServices {

  def SaveOrUpdate(company: CompanyLogo): Future[ResultSet] = {
    OrganisationLogoRepository.save(company)
  }

  def findDCompanyLogo(company: String, id: String): Future[Option[CompanyLogo]] = {
    OrganisationLogoRepository.findDCompanyLogo(company, id)
  }

  def findCompanyLogos(company: String): Future[Seq[CompanyLogo]] = {
    OrganisationLogoRepository.findCompanyLogos(company)
  }

}
