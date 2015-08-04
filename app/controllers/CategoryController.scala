package controllers

import domain.Category
import model.CategoryModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.CategoryService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/16.
 */
class CategoryController extends Controller {

  val service = CategoryService()

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val categoryModel = Json.fromJson[CategoryModel](input).get
      val category = categoryModel.getDomain()
      val results = service.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }

  def getAll = Action.async {
    request =>
      service.getCategories map (categories => Ok(Json.toJson(categories)))
  }

  def getCategory(id: String) = Action.async {
    service.getCategory(id) map (category => Ok(Json.toJson(category)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[Category](input).get
      val results = service.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }
}
