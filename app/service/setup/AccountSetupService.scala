package service.setup

import conf.util.Util
import domain.common.demographics.Role
import domain.common.demographics.RolesList._
import domain.people.{Person, PersonRole}
import repository.common.demographics.RoleRepository
import repository.people.{PersonRepository, PersonRoleRepository}
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object AccountSetupService extends Service {


  def createRoles = {
    val admin = Role(ROLE_ADMIN, ROLE_ADMIN, ADMIN,"ACTIVE")
    val prospect = Role(ROLE_PROSPECT, ROLE_PROSPECT, PROSPECT,"ACTIVE")
    val hrmanager = Role(ROLE_HRMANAGER, ROLE_HRMANAGER, HRMANAGER,"ACTIVE")
    val companyadmin = Role(ROLE_COMPANY_ADMIN, ROLE_COMPANY_ADMIN, COMPANY_ADMIN,"ACTIVE")
    val hrstaff = Role(ROLE_HRSTAFF, ROLE_HRSTAFF, HRSTAFF,"ACTIVE")
    val manager = Role(ROLE_MANAGER, ROLE_MANAGER, MANAGER,"ACTIVE")
    val employee = Role(ROLE_EMPLOYEE, ROLE_EMPLOYEE, EMPLOYEE,"ACTIVE")
    val trainer = Role(ROLE_TRAINER, ROLE_TRAINER, TRAINER,"ACTIVE")
    val panelist = Role(ROLE_PANELIST, ROLE_PANELIST, PANELIST,"ACTIVE")
    val repo = RoleRepository
    for {
      result1 <- repo.save(admin)
      result2 <- repo.save(prospect)
      result3 <- repo.save(hrmanager)
      result4 <- repo.save(companyadmin)
      result5 <- repo.save(hrstaff)
      result6 <- repo.save(manager)
      result7 <- repo.save(employee)
      result8 <- repo.save(trainer)
      result9 <- repo.save(panelist)

    } yield result9

  }

  def createAdmin = {

    val person = Person(
      "HASHCODE",
      Util.md5Hash("ADMIN"),
      "System",
      "Admin",
      "admin@test.com",
      "Administrator", "MR", Util.encode("admin"), true, true, true, true,"ACTIVE")

    val personrole = PersonRole(person.id, ROLE_ADMIN,"ACTIVE")

    val prepo = PersonRepository
    val rrepo = PersonRoleRepository

    for {
      r <- prepo.save(person)
      pr <- rrepo.save(personrole)
    }
      yield pr

  }

}
