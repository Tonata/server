package service

import domain.User
import repository.UserRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/09.
 */
trait UserService {
  def create(user:User)
  def getUser(id:String):Future[Option[User]]
  def getUsers:Future[Seq[User]]
  def changePassword(password:String)
}

object UserService{
  def apply():UserService = new UserServiceImpl
  private class UserServiceImpl extends UserService{
    override def create(user: User): Unit = {
      UserRepository.save(user)
    }

    override def getUser(id: String): Future[Option[User]] = {
      UserRepository.getUserById(id)
    }

    override def getUsers: Future[Seq[User]] = {
      UserRepository.getAllUsers
    }

    override def changePassword(password: String): Unit = {

    }
  }
}
