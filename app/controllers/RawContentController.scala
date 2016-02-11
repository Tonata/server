package controllers

import domain.RawContent
import model.RawContentModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.RawContentService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/02/11.
  */
class RawContentController extends Controller{
  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentModel = Json.fromJson[RawContentModel](input).get
      val content = contentModel.getDomain()
      val results = RawContentService.create(content)
      results map(result =>Ok(Json.toJson(content)))
  }

  def getAll = Action.async {
    request =>
      RawContentService.getAllContent map (contents => Ok(Json.toJson(contents)))
  }

  def getContent(id: String) = Action.async {
    RawContentService.getContent(id) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[RawContent](input).get
      val results = RawContentService.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }

}
