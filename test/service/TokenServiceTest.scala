package service

import com.auth0.jwt.JWTVerifier
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by hashcode on 2015/06/09.
 */
class TokenServiceTest extends FeatureSpec with GivenWhenThen{
  feature("Create Token ") {

    info("As a User ")
    info("I want to Create a Token")
    info("So that I can Store User Information")

    scenario("Create a Token") {


      Given(" Given The Token Information")

      val info = Map(
        "issuer"->"kujali.cput.ac.za",
        "subject"->"info@kujali.cput.ac.za",
        "signiture"->"test123",
        "roles"->"CAREGIVER,NURSE")

      When(" When I call the Create Token API")

      val token = Await.result(TokenService().createToken(info),100.nanos)

      Then(" Then I should Receive a Token")

      println(" The Token received ", token )

      And("And Verify that it is a correect Token")



      val verified =  new JWTVerifier("test123").verify(token)

    }

  }


}
