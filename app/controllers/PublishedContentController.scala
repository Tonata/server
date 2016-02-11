package controllers

import domain.PublishedContent
import model.PublishedContentModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.PublishedContentService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/02/11.
  */
class PublishedContentController extends Controller{
  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentModel = Json.fromJson[PublishedContentModel](input).get
      val content = contentModel.getDomain()
      val results = PublishedContentService.create(content)
      results map(result =>Ok(Json.toJson(content)))
  }

  def getAll = Action.async {
    request =>
      PublishedContentService.getAllContent map (contents => Ok(Json.toJson(contents)))
  }

  def getContent(id: String) = Action.async {
    PublishedContentService.getContent(id) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[PublishedContent](input).get
      val results = PublishedContentService.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }


}
