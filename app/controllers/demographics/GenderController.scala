package controllers.demographics

import domain.demographics.Gender
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.demographics.GenderService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class GenderController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>

      val entity = Json.fromJson[Gender](request.body).get
      GenderService.saveOrUpdate(entity) map (result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      GenderService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      GenderService.getAll map (result =>
        Ok(Json.toJson(result)))
  }
}
