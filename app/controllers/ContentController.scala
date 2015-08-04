package controllers

import domain.{Content}
import model.{ContentModel}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.{ContentService}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/16.
 */
class ContentController extends Controller{
  val service = ContentService()

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentModel = Json.fromJson[ContentModel](input).get
      val content = contentModel.getDomain()
      val results = service.create(content)
      results map(result =>Ok(Json.toJson(content)))
  }

  def getAll = Action.async {
    request =>
      service.getAllContent map (contents => Ok(Json.toJson(contents)))
  }

  def getContent(id: String) = Action.async {
    service.getContent(id) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[Content](input).get
      val results = service.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }

  def isPlublished(id: String) = Action.async {
    service.isPublished(id) map (content => Ok(Json.toJson(content)))
  }

  def isEditedAndPlublished(id: String) = Action.async {
    service.isInEditOrPublished(id) map (content => Ok(Json.toJson(content)))
  }

  def getContentByCategory(id: String, initValue:String) = Action.async {
    service.getContentsByCateGory(initValue.toInt,id) map (content => Ok(Json.toJson(content.toSeq)))
  }

  def getContentByTypeId(id: String, initValue:String) = Action.async {
    service.getContentByType(id, initValue.toInt) map (content => Ok(Json.toJson(content.toSeq)))
  }

  def getRelatedContent(id: String) = Action.async {
    service.relatedItems(id)map (content => Ok(Json.toJson(content)))
  }
}
