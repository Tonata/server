package service

import java.util
import java.util.UUID

import com.auth0.jwt.JWTSigner
import com.github.nscala_time.time.Imports._
import conf.util.Util
import domain.Token
import repository.TokenResposiory

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/09.
 */
trait TokenService {
  def saveToken(token: Token)

  def getTokenById(id: String): Future[Option[Token]]

  def revokeToken(id: String)
  def getTokenRole(token:String)

  def getTokenId(token:String)

  def isTokenValid(token:String):Boolean

  def createToken(info: Map[String, String]): String
}

object TokenService {
  def apply(): TokenService = new TokenServiceImpl

  private class TokenServiceImpl extends TokenService {
    override def saveToken(token: Token): Unit = {
      TokenResposiory.save(token)

    }

    override def getTokenById(id: String): Future[Option[Token]] = {
      TokenResposiory.getTokenById(id)
    }

    override def revokeToken(id: String): Unit = {
      TokenResposiory.deleteToken(id)
    }

    override def createToken(info: Map[String, String]): String = {
      val duration = (24.hours + 45.minutes + 10.seconds).millis
      val issueDate = (DateTime.now to DateTime.nextSecond).millis
      val signiture = new JWTSigner(info.get("signiture").get)
      val claims = new util.HashMap[String, Object]()
      claims.put("iss", info.get("issuer").get)
      claims.put("sub", info.get("subject").get)
      claims.put("exp", new Integer(duration.toString))
      claims.put("iat", new Integer(issueDate.toString))
      claims.put("jit", Util.md5Hash(UUID.randomUUID().toString()))
      claims.put("role", info.get("roles").get)
      val token = signiture.sign(claims, new JWTSigner.Options()
        .setExpirySeconds(50).setNotValidBeforeLeeway(10).setIssuedAt(true))
      token
    }

    override def getTokenRole(token: String): Unit = ???

    override def isTokenValid(token: String): Boolean = ???

    override def getTokenId(token: String): Unit = ???
  }

}
