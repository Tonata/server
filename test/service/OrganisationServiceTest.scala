package service

import java.util.Date

import domain.organisation.{Organisation, OrganisationLogo}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.organisation.OrganisationRepository
import service.organisation.OrganisationService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent._
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by user42 on 2016/09/08.
  */
class OrganisationServiceTest extends FeatureSpec with GivenWhenThen{
  feature(""){

    info("As a user")
    info("I want to be able to view the organization details")

    scenario("Test"){
      Given("Organization record")

      val mapCoordinates = Map("34.157789" -> "19.015227")

      val newOrganization = Organisation("111", "UN", mapCoordinates,
                                "", new Date(), "state")

      val organisationService = OrganisationService
      organisationService.saveOrUpdate(newOrganization)

      val organisation = organisationService.getById("111")
//      val result  = Await.result(organisation, 1 seconds)

      organisation map {
        org => org match {
          case Some(x) => {
            assert(x.name === "UN")}
        }

      }


    }
  }

}
