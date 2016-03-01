package controllers.person

import domain.person.PersonLanguage
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.person.PersonLanguageService

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonLanguageController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonLanguage](input).get
      val results = PersonLanguageService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonLanguageService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonLanguageService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
