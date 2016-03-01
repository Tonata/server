package controllers.person

import domain.person.PersonRole
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.person.PersonRoleService

/**
 * Created by hashcode on 2015/11/09.
 */
class PersonRoleController extends Controller {

  def createOrUpdate = Action.async(parse.json) {

    request =>
      val input = request.body
      val entity = Json.fromJson[PersonRole](input).get
      val results = PersonRoleService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String,roleId:String) = Action.async {
    request =>
      PersonRoleService.get(id,roleId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllPersonRoles(id:String) = Action.async {
    request =>
      PersonRoleService.getAllRoles(id) map (result =>
        Ok(Json.toJson(result)))
  }

}
