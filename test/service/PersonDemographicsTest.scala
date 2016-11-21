package service

import java.util.Date

import domain.demographics.{Gender, Language, Race}
import domain.location.{AddressType, ContactType}
import domain.person._
import org.scalatest.{FeatureSpec, GivenWhenThen}
import service.demographics.{GenderService, LanguageService, RaceService}
import service.location.{AddressTypeService, ContactTypeService}
import service.person._

import scala.util.{Failure, Success}

/**
  * Created by user42 on 2016/09/09.
  */
class PersonDemographicsTest extends FeatureSpec with GivenWhenThen{
  feature("Creating user profile"){

    info("As a user")
    info("I want to see what race I entered")

    scenario("Test"){
      Given("Person and Person Demographics record")

      val personRecord = Person ("HBC","123", "John",
        "john@example.com", "Doe" , "*",
        true,false,true,true, "the state")

      val gender = Gender("GEN01", "Male", "")

      val race = Race("R1", "BLACK", "")

      val personDemo = PersonDemographics("Demo1", "123", "GEN01",
        "R1", new Date (1989, 2, 12), "1", 5, new Date(), "current-state")

      val personService     = PeopleService
      val genderService     = GenderService
      val raceService       = RaceService
      val personDemoService = PersonDemographicsService

      personService.saveOrUpdate(personRecord)
      genderService.saveOrUpdate(gender)
      raceService.saveOrUpdate(race)
      personDemoService.saveOrUpdate(personDemo)

      val futureRes         = raceService.get("R1")

//      futureRes.onComplete{
//        case Success(result) => println(s"Success $result")
//        case Failure(throwable) => println(s"Failure $throwable")
//      }

    }
  }
}
