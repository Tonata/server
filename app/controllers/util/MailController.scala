package controllers.util

import domain.util.Mail
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.util.MailService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/28.
 */
class MailController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Mail](input).get
      val results = MailService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(org:String,id: String) = Action.async {
    request =>
      MailService.get(org,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll(org:String) = Action.async {
    request =>
      MailService.getAll(org) map (result =>
        Ok(Json.toJson(result)))
  }

}