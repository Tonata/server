package controllers.location

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
 * Created by hashcode on 2015/11/09.
 */
class ContactTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[ContactType](input).get
      val results = ContactTypeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      ContactTypeService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      ContactTypeService.getAll map (result =>
        Ok(Json.toJson(result)))
  }

}
