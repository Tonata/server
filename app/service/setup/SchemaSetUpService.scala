package service.setup

import repository.demographics._
import repository.location.{ContactTypeRepository, AddressTypeRepository, LocationTypeRepository}
import repository.organisation.{OrganisationLogoRepository, OrganisationRepository}
import repository.person._
import repository.storage.StorageUrlRepository
import repository.util.{TokenRepository, KeysRepository, MailRepository, StatusRepository}
import service.Service
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object SchemaSetUpService extends Service {

  def createCompanySchema = for {
  //company
    person <- PersonRepository.create.ifNotExists().future()


    // demo
    gender <- GenderRepository.create.ifNotExists().future()
    lanp <- LanguageProficiencyRepository.create.ifNotExists().future()
    //companycontacts
    lang <- LanguageRepository.create.ifNotExists().future()
    depart <- RaceRepository.create.ifNotExists().future()
    role <- RoleRepository.create.ifNotExists().future()
    //companycontacts
    title <- StatusRepository.create.ifNotExists().future()
    preole <- PersonRoleRepository.create.ifNotExists().future()

    //location
    race <- RaceRepository.create.ifNotExists().future()
    role <- RoleRepository.create.ifNotExists().future()

    //util
    status <- StatusRepository.create.ifNotExists().future()

    //people
    emailp <- UsersRepository.create.ifNotExists().future()
    company <- OrganisationRepository.create.ifNotExists().future()
    locatype <- LocationTypeRepository.create.ifNotExists().future()
    addretype <- AddressTypeRepository.create.ifNotExists().future()
    mail <- MailRepository.create.ifNotExists().future()
    ctypes <- ContactTypeRepository.create.ifNotExists().future()
    pa <- PersonAddressRepository.create.ifNotExists().future()
    pcont <- PersonContactRepository.create.ifNotExists().future()
    pdemo <- PersonDemographicsRepository.create.ifNotExists().future()

    plang <- PersonLanguageRepository.create.ifNotExists().future()
    clogos <- OrganisationLogoRepository.create.ifNotExists().future()
    clinks <- StorageUrlRepository.create.ifNotExists().future()
    tokenkeys <-KeysRepository.create.ifNotExists().future()
    token <-TokenRepository.create.ifNotExists().future()

  } yield person
}
