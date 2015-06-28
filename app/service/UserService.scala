package service

import domain.User

/**
 * Created by hashcode on 2015/06/09.
 */
trait UserService {
  def create(user:User)
  def getUser(id:String):User
  def getUsers:List[User]
  def update(user:User)
  def changePassword(password:String)
}

object UserService{
  def apply():UserService = new UserServiceImpl
  private class UserServiceImpl extends UserService{
    override def create(user: User): Unit = ???

    override def update(user: User): Unit = ???

    override def getUser(id: String): User = ???

    override def getUsers: List[User] = ???

    override def changePassword(password: String): Unit = ???
  }
}
