package controllers.demographics

import domain.demographics.Role
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.demographics.RoleService

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/28.
 */
class RoleController extends Controller{
  val service = RoleService

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val role = Json.fromJson[Role](input).get
      val results = service.saveOrUpdate(role)
      results map(result =>Ok(Json.toJson(role)))
  }

  def getAll = Action.async {
    request =>
      service.getAll map (roles => Ok(Json.toJson(roles)))
  }

  def getRole(id: String) = Action.async {
    service.get(id) map (roles => Ok(Json.toJson(roles)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val role = Json.fromJson[Role](input).get
      val results = service.saveOrUpdate(role)
      results map(result =>Ok(Json.toJson(role)))
  }
}