package service.person

import java.util.Date

import domain.demographics.{Gender, Language, Race}
import domain.location.{AddressType, ContactType}
import domain.person._
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatestplus.play.PlaySpec
import service.demographics.{GenderService, LanguageService}
import service.location.{AddressTypeService, ContactTypeService}

/**
  * Created by user42 on 2016/09/08.
  */
class PersonServiceTest extends PlaySpec{

  "PersonSrevice # getById" should{
    "be true when the person id exists" in {

      val personRecord = Person ("HBC","123", "John",
        "john@example.com", "Doe" , "*",
        true,false,true,true, "the state")


      val addressType = AddressType ("ADT001", "Residential", "")
      val personAddress = PersonAddress("Add1", "123", "1 Tenant Street",
        "7925", "ADT001", new Date(), "")

      val language = Language("LANG1", "english", "")
      val personLang = PersonLanguage("001", "123", "LANG1", "English",
        "English", "English", new Date(),
        "")

      val gender = Gender("GEN01", "Male", "")
      val raceID = Race("R1", "BLACK", "")
      val personDemo = PersonDemographics("Demo1", "123", "GEN01",
        "R1", new Date (1989, 2, 12), "1", 5, new Date(), "current-state")

      val contactType = ContactType("CON001", "cell", "")
      val personContact = PersonContact("1", "123", "CON001",
        "0784117523", "Active",
        new Date(), "")


      val personService = PeopleService
      personService.saveOrUpdate(personRecord)

      val personAddressService = PersonAddressService
      personAddressService.saveOrUpdate(personAddress)

      val addressService    = AddressTypeService
      addressService.saveOrUpdate(addressType)

      val langService = LanguageService
      langService.saveOrUpdate(language)

      val personLangService = PersonLanguageService
      personLangService.saveOrUpdate(personLang)

      val genderServ = GenderService
      genderServ.saveOrUpdate(gender)

      val personDemoService = PersonDemographicsService
      personDemoService.saveOrUpdate(personDemo)

      val contact = ContactTypeService
      contact.saveOrUpdate(contactType)

      val personContactServ = PersonContactService
      personContactServ.saveOrUpdate(personContact)



    }
  }

  /*feature("Creating user profile"){

    info("As a user")
    info("I want to add details to my profile")

    scenario("Test"){
      Given("")

      // Object creation
      val personRecord = Person ("HBC","123", "John",
                                  "john@example.com", "Doe" , "*",
                                  true,false,true,true, "the state")

      val addressType = AddressType ("ADT001", "Residential", "")
      val personAddress = PersonAddress("Add1", "123", "1 Tenant Street",
                                        "7925", "ADT001", new Date(), "")

      val language = Language("LANG1", "english", "")
      val personLang = PersonLanguage("001", "123", "LANG1", "English",
                                      "English", "English", new Date(),
                                      "")

      val gender = Gender("GEN01", "Male", "")
      val raceID = Race("R1", "BLACK", "")
      val personDemo = PersonDemographics("Demo1", "123", "GEN01",
        "R1", new Date (1989, 2, 12), "1", 5, new Date(), "current-state")

      val contactType = ContactType("CON001", "cell", "")
      val personContact = PersonContact("1", "123", "CON001",
                                        "0784117523", "Active",
                                        new Date(), "")
      // Use of objects in services
      val personService = PeopleService
      personService.saveOrUpdate(personRecord)

      val personAddressServ = PersonAddressService
      val addressService    = AddressTypeService
      addressService.saveOrUpdate(addressType)
      personAddressServ.saveOrUpdate(personAddress)

      val langService = LanguageService
      val personLangService = PersonLanguageService
      langService.saveOrUpdate(language)
      personLangService.saveOrUpdate(personLang)

      val genderServ = GenderService
      val personDemoService = PersonDemographicsService
      genderServ.saveOrUpdate(gender)
      personDemoService.saveOrUpdate(personDemo)

      val contact = ContactTypeService
      val personContactServ = PersonContactService
      contact.saveOrUpdate(contactType)
      personContactServ.saveOrUpdate(personContact)

      val futureRes = personService.getPerson("HBC", "123")

//      futureRes.onComplete{
//        case Success(result) => println(s"Success $result")
//        case Failure(throwable) => println(s"Failure $throwable")
//      }

    }
  }*/
}
