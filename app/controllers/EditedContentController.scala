package controllers

import domain.EditedContent
import model.EditedContentModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.EditedContentService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/02/11.
  */
class EditedContentController extends Controller{
  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentModel = Json.fromJson[EditedContentModel](input).get
      val content = contentModel.getDomain()
      val results = EditedContentService.create(content)
      results map(result =>Ok(Json.toJson(content)))
  }

  def getAll = Action.async {
    request =>
      EditedContentService.getAllContent map (contents => Ok(Json.toJson(contents)))
  }

  def getContent(id: String) = Action.async {
    EditedContentService.getContent(id) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[EditedContent](input).get
      val results = EditedContentService.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }

}
