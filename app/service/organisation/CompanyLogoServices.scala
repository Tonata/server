package service.organisation

import com.datastax.driver.core.ResultSet
import repository.organisation.CompanyLogoRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/03.
 */
object CompanyLogoServices {

  def SaveOrUpdate(company: CompanyLogo): Future[ResultSet] = {
    CompanyLogoRepository.save(company)
  }

  def findDCompanyLogo(company: String, id: String): Future[Option[CompanyLogo]] = {
    CompanyLogoRepository.findDCompanyLogo(company, id)
  }

  def findCompanyLogos(company: String): Future[Seq[CompanyLogo]] = {
    CompanyLogoRepository.findCompanyLogos(company)
  }

}
