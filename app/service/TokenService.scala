package service

import domain.Token

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/09.
 */
trait TokenService {
  def createToken(token:Token)
  def getToken(id:String):Future[Option[Token]]
  def revokeToken(id:String)
}

object TokenService {
  def apply(): TokenService = new TokenServiceImpl

  private class TokenServiceImpl extends TokenService {
    override def createToken(token: Token): Unit = {

    }

    override def getToken(id: String): Future[Option[Token]] = ???

    override def revokeToken(id: String): Unit = {

    }
  }

}
