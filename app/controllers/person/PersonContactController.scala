package controllers.person

import domain.person.PersonContact
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.person.PersonContactService

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonContactController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonContact](input).get
      val results = PersonContactService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonContactService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonContactService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }

}
