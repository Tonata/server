package service.setup

import conf.util.Util
import domain.demographics.Role
import domain.person.{Person, PersonRole}
import domain.demographics.RolesList._
import repository.demographics.RoleRepository
import repository.person.{PersonRepository, PersonRoleRepository}
import service.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/07.
 */
object AccountSetupService extends Service {



  def createRoles = {
    val admin = Role(ROLE_ADMIN, ROLE_ADMIN, ADMIN,"ACTIVE")
    val editor = Role(ROLE_EDITOR, ROLE_EDITOR, ADMIN,"ACTIVE")
    val publisher = Role(ROLE_PUBLISHER, ROLE_PUBLISHER, ADMIN,"ACTIVE")
    val orgadmin = Role(ORG_ADMIN, ORG_ADMIN, ADMIN,"ACTIVE")

    val repo = RoleRepository
    for {
      result1 <- repo.save(admin)
      result2 <- repo.save(editor)
      result3 <- repo.save(publisher)
      result4 <- repo.save(orgadmin)
    } yield result1

  }

  def createAdmin = {

    val person = Person(
      "CPUT",
      Util.md5Hash("ADMIN"),
      "System",
      "admin@test.com",
      "Administrator",
      Util.encode("admin"),
      true,
      true,
      true,
      true,
      "ACTIVE")

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
