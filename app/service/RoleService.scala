package service

import domain.Role
import repository.RoleRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/28.
 */
trait RoleService {
  def getRole(id:String):Future[Option[Role]]
  def create(role:Role)
  def getRoles:Future[Seq[Role]]
}

object RoleService{
  def apply():RoleService= new RoleServiceImpl
  private class RoleServiceImpl extends RoleService{
    override def getRole(id: String): Future[Option[Role]] = {
      RoleRepository.getRoleById(id)
    }

    override def getRoles: Future[Seq[Role]] = {
      RoleRepository.getRoles
    }

    override def create(role: Role): Unit = {
      RoleRepository.save(role)
    }
  }
}
