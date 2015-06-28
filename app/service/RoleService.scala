package service

import domain.Role

/**
 * Created by hashcode on 2015/06/28.
 */
trait RoleService {
  def getRole(id:String):Role
  def create(role:Role)
  def update(role:Role)
  def getRoles:List[Role]
}

object RoleService{
  def apply():RoleService= new RoleServiceImpl
  private class RoleServiceImpl extends RoleService{
    override def getRole(id: String): Role = ???

    override def getRoles: List[Role] = ???

    override def update(role: Role): Unit = ???

    override def create(role: Role): Unit = ???
  }
}
