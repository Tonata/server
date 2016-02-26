package service.setup

import repository.common.demographics._
import repository.common.education.{DegreeTypeRepository, EducationTypeRepository, EvaluationRepository}
import repository.common.job.JobClassificationRepository
import repository.common.location.{AddressTypeRepository, ContactTypeRepository, LocationTypeRepository}
import repository.common.position.PositionTypeRepository
import repository.common.util.{CurrencyRepository, MailRepository, StatusRepository}
import repository.company.{CompanyLogoRepository, CompanyRepository, DepartmentRepository, OfficeRepository}
import repository.contacts.ContactsRepository
import repository.job.{JobEventRepository, JobRepository}
import repository.payroll.salary.NotchRepository
import repository.people._
import repository.position._
import repository.storage.StorageUrlRepository
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object SchemaSetUpService extends Service {

  def createCompanySchema = for {
  //company
    person <- PersonRepository.create.ifNotExists().future()
    depart <- DepartmentRepository.create.ifNotExists().future()
    office <- OfficeRepository.create.ifNotExists().future()
    //companycontacts
    contacts <- ContactsRepository.create.ifNotExists().future()
    // demo
    gender <- GenderRepository.create.ifNotExists().future()
    id <- IdentificationTypeRepository.create.ifNotExists().future()
    lanp <- LanguageProficiencyRepository.create.ifNotExists().future()
    //companycontacts
    lang <- LanguageRepository.create.ifNotExists().future()
    marital <- MaritalStatusRepository.create.ifNotExists().future()
    depart <- RaceRepository.create.ifNotExists().future()
    role <- RoleRepository.create.ifNotExists().future()
    //companycontacts
    title <- StatusRepository.create.ifNotExists().future()
    preole <- PersonRoleRepository.create.ifNotExists().future()

    //education
    eduty <- EducationTypeRepository.create.ifNotExists().future()
    eval <- EvaluationRepository.create.ifNotExists().future()

    // job
    jobC <- JobClassificationRepository.create.ifNotExists().future()
    job <- JobRepository.create.ifNotExists().future()
    jobEvent <- JobEventRepository.create.ifNotExists().future()




    //location
    race <- RaceRepository.create.ifNotExists().future()
    role <- RoleRepository.create.ifNotExists().future()

    //position
    postype <- PositionTypeRepository.create.ifNotExists().future()
    title <- TitleRepository.create.ifNotExists().future()
    posi <- PositionRepository.create.ifNotExists().future()
    dpR <- DepartureReasonRepository.create.ifNotExists().future()
    posDes <- PositionDesignationRepository.create.ifNotExists().future()
    posEvent <- PositionEventRepository.create.ifNotExists().future()
    posOcc <- PositionOccupantsRepository.create.ifNotExists().future()
    posiPack <- PositionPackageRepository.create.ifNotExists().future()


    //util
    status <- StatusRepository.create.ifNotExists().future()
    currency <- CurrencyRepository.create.ifNotExists().future()

    //people
    emailp <- UsersRepository.create.ifNotExists().future()
    company <- CompanyRepository.create.ifNotExists().future()
    locatype <- LocationTypeRepository.create.ifNotExists().future()
    addretype <- AddressTypeRepository.create.ifNotExists().future()
    mail <- MailRepository.create.ifNotExists().future()
    ctypes <- ContactTypeRepository.create.ifNotExists().future()
    degreeType <- DegreeTypeRepository.create.ifNotExists().future()
    pa <- PersonAddressRepository.create.ifNotExists().future()
    pcont <- PersonContactRepository.create.ifNotExists().future()
    pced <- PersonContinuingEducationRepository.create.ifNotExists().future()
    pdemo <- PersonDemographicsRepository.create.ifNotExists().future()
    pedu <- PersonEducationHistoryRepository.create.ifNotExists().future()
    pemp <- PersonEmploymentHistoryRepository.create.ifNotExists().future()
    pid <- PersonIdentityRepository.create.ifNotExists().future()
    plang <- PersonLanguageRepository.create.ifNotExists().future()
    clogos <- CompanyLogoRepository.create.ifNotExists().future()
    clinks <- StorageUrlRepository.create.ifNotExists().future()
    pposi <- PersonPositionRepository.create.ifNotExists().future()
    notch <- NotchRepository.create.ifNotExists().future()


  } yield person
}
