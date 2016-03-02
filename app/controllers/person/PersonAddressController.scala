package controllers.person

import domain.person.PersonAddress
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.person.PersonAddressService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonAddressController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonAddress](input).get
      val results = PersonAddressService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonAddressService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonAddressService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
