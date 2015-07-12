package service

import com.datastax.driver.core
import domain.{User, Role}
import repository.RoleRepository
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/28.
 */
trait RoleService {
  def getRole(id: String): Future[Option[Role]]

  def create(role: Role): Future[core.ResultSet]

  def getRoles: Future[Seq[Role]]

  def getUserRoles(user: User): Set[Future[String]]
}

object RoleService {
  def apply(): RoleService = new RoleServiceImpl

  private class RoleServiceImpl extends RoleService {
    override def getRole(id: String): Future[Option[Role]] = {
      RoleRepository.getRoleById(id)
    }

    override def getRoles: Future[Seq[Role]] = {
      RoleRepository.getRoles
    }

    override def create(role: Role): Future[core.ResultSet] = {
      RoleRepository.save(role)
    }

    override def getUserRoles(user: User): Set[Future[String]] = {
      val roleIds = user.roles
      val userRoles = roleIds map (id => getRole(id))
      val rolesString = userRoles map (roles => roles map (userRole => userRole match {
        case Some(role) => role.roleName
        case None => ""
      }))
      rolesString
    }
  }
}
