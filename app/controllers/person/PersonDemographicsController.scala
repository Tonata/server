package controllers.person

import domain.person.PersonDemographics
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.person.PersonDemographicsService

/**
 * Created by hashcode on 2015/12/17.
 */
class PersonDemographicsController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[PersonDemographics](input).get
      val results = PersonDemographicsService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String, roleId: String) = Action.async {
    request =>
      PersonDemographicsService.getPersonValue(id, roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllValues(personId: String) = Action.async {
    request =>
      PersonDemographicsService.getValues(personId) map (result =>
        Ok(Json.toJson(result)))
  }
}
