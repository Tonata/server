package api

import java.util.Date

import com.google.gson.Gson
import controllers.organisation.OrganisationController
import domain.organisation.{Organisation, OrganisationModel}
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by user42 on 2016/09/27.
  */
@RunWith(classOf[JUnitRunner])
class OrganisationControllerTest extends Specification{

  val gson = new Gson()

  "Organisation Controller" should {

    "should save" in new WithApplication {

      val organisation = Organisation("5", "NGO", Map("34.157789" -> "19.015227"),
        "", new Date(), "current state")

      val jsonString = gson.toJson(organisation).stripMargin
      val json: JsValue = Json.obj (
      ("organisation" -> jsonString)
      )

      val Some(result) = route(FakeRequest(
       POST, "/api/organisation/post").withJsonBody(json))

       status(result) must be equalTo(OK)
       Logger.debug("The result is " + result)
       contentType(result) must beSome("application/json")

    }

  }



}
