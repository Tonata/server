package service.util

import java.util.UUID

import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import com.datastax.driver.core
import com.github.nscala_time.time.Imports._
import conf.util.Util
import domain.util.Token
import repository.util.TokenRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
 * Created by hashcode on 2015/06/09.
 */
trait TokenService {
  def saveToken(token: Token):Future[core.ResultSet]

  def getTokenById(id: String): Future[Option[Token]]

  def revokeToken(id: String)

  def getTokenRoles(token: String): String

  def getTokenId(token: String): String

  def isTokenValid(token: String): Future[Boolean]

  def createToken(info: Map[String, String]): Future[String]
}

object TokenService {
  def apply(): TokenService = new TokenServiceImpl

  private class TokenServiceImpl extends TokenService {

    val keyvv = KeyService().getKey() map {
      values => values.head.value
    }

    override def saveToken(token: Token): Future[core.ResultSet] = {
      TokenRepository.save(token)

    }

    override def getTokenById(id: String): Future[Option[Token]] = {
      TokenRepository.getTokenById(id)
    }

    override def revokeToken(id: String): Unit = {
      TokenRepository.deleteToken(id)
    }

    override def createToken(info: Map[String, String]): Future[String] = {
      val duration = (24.hours + 45.minutes + 10.seconds).millis
      val issueDate = (DateTime.now to DateTime.nextSecond).millis

      val header = JwtHeader("HS256", "FidHub")
      val claims = JwtClaimsSet(Map(
        "iss" -> "hashcode.zm",
        "sub" -> info("subject"),
        "roles" -> info("roles"),
        "exp" -> duration,
        "iat" -> issueDate,
        "jit" -> Util.md5Hash(UUID.randomUUID().toString)
      ))

      val token = for (
        signiture <- keyvv;
        generatedToken <- Future{JsonWebToken(header, claims, signiture)}
      ) yield generatedToken
      token
    }

    override def getTokenRoles(token: String): String = {
      val claims = getClaims(token)
      val roles = claims.getOrElse(Map.empty[String, String]).get("roles")
      val value = roles match {
        case Some(role)=>role
        case None=>"NONE"
      }
      value
    }

    override def isTokenValid(token: String): Future[Boolean] = {
      val isValid = for (
        signiture <- keyvv;
        status <- Future{JsonWebToken.validate(token, signiture)}
      ) yield status
      isValid
    }

    override def getTokenId(token: String): String = {
      val claims = getClaims(token)
      val tokenId = claims.getOrElse(Map.empty[String, String]).get("jit")

      val tokenValue = tokenId match {
        case Some(id)=>id.toString
        case None=>""
      }
      tokenValue
    }
    private def getClaims(token: String): Option[Map[String, String]] = {
      token match {
        case JsonWebToken(header, claims, signature) =>
          claims.asSimpleMap.toOption
        case x =>
          None
      }
    }
  }

}
