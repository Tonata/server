package controllers

import domain.ContentType
import model.ContentTypeModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.ContentTypeService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/28.
 */
class ContntTypeController extends Controller{
  val service = ContentTypeService()

  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentModel = Json.fromJson[ContentTypeModel](input).get
      val content = contentModel.getDomain()
      val results = service.create(content)
      results map(result =>Ok(Json.toJson(content)))
  }

  def getAll = Action.async {
    request =>
      service.getContentTypes map (contentTypes => Ok(Json.toJson(contentTypes)))
  }

  def getContentType(id: String) = Action.async {
    service.getContentType(id) map (contentType => Ok(Json.toJson(contentType)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentType = Json.fromJson[ContentType](input).get
      val results = service.create(contentType)
      results map(result =>Ok(Json.toJson(contentType)))
  }
}