package controllers.demographics

import domain.demographics.LanguageProficiency
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.demographics.LanguageProficiencyService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class LanguageProficiencyController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[LanguageProficiency](input).get
      val results = LanguageProficiencyService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      LanguageProficiencyService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      LanguageProficiencyService.getAll map (result =>
        Ok(Json.toJson(result)))
  }

}
