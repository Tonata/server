package controllers.content

import domain.content.{EditedContent, PublishedContent}
import model.{EditedContentModel, PublishedContentModel}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.content.{EditedContentService, PublishedContentService}

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

  def getAll(org:String) = Action.async {
    request =>
      PublishedContentService.getAllContents(org) map (contents => Ok(Json.toJson(contents)))
  }

  def getContentById(org:String, id:String) = Action.async {
    PublishedContentService.getContentById(org,id) map (content => Ok(Json.toJson(content)))
  }

  def getContents(org: String,initV:Int) = Action.async {
    PublishedContentService.getContents(org,initV.toInt) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[PublishedContent](input).get
      val results = PublishedContentService.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }
}
