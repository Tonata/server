package controllers.content

import domain.content.EditedContent
import model.EditedContentModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.content.EditedContentService

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

  def getAll(org:String) = Action.async {
    request =>
      EditedContentService.getAllContents(org) map (contents => Ok(Json.toJson(contents)))
  }

  def getContentById(org:String, id:String) = Action.async {
    EditedContentService.getContentById(org,id) map (content => Ok(Json.toJson(content)))
  }

  def getContents(org: String,initV:Int) = Action.async {
    EditedContentService.getContents(org,initV.toInt) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[EditedContent](input).get
      val results = EditedContentService.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }
}
