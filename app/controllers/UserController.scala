package controllers

import domain.User
import model.UserModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.UserService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/16.
 */
class UserController extends Controller{
  val service = UserService()

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val userModel = Json.fromJson[UserModel](input).get
      val user = userModel.getDomain()
      val results = service.create(user)
      results map(result =>Ok(Json.toJson(user)))
  }

  def getAll = Action.async {
    request =>
      service.getUsers map (users => Ok(Json.toJson(users)))
  }

  def getUser(id: String) = Action.async {
    service.getUser(id) map (user => Ok(Json.toJson(user)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[User](input).get
      val results = service.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }
}