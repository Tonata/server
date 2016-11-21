package service

import java.util.Date

import domain.organisation.{Organisation, OrganisationLogo}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import service.organisation.{OrganisationLogoServices, OrganisationService}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by user42 on 2016/09/09.
  */
class OrganisationLogoTest extends FeatureSpec with GivenWhenThen{
  feature(""){

    info("As a user")
    info("I want to be able to view the organization's logo")

    scenario("Test"){
      Given("An organization record")

      val logoURL = "URL"

      val mapCoordinates = Map("34.157789" -> "19.015227")

      val newOrg = Organisation("123", "ELF", mapCoordinates,
        "", new Date(), "state")

      val newOrgLogo = OrganisationLogo("ELF", "123", logoURL,
        Some(""), "this logo",
        "mime", new Date())

      val organisationService     = OrganisationService
      val organizationLogService  = OrganisationLogoServices
      organisationService.saveOrUpdate(newOrg)
      organizationLogService.SaveOrUpdate(newOrgLogo)

      val org = organizationLogService.findDCompanyLogo("ELF","123")

      org map {
        o => o match {
          case Some(x) => {
            assert(x.url === logoURL)}
        }
      }

    }
  }


}
