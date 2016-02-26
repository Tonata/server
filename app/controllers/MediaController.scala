package controllers

import domain.content.Media
import model.MediaModel
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.content.MediaService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/02/11.
  */
class MediaController  extends Controller{
  def create = Action.async(parse.json) {
    request =>
      val input = request.body
      val contentModel = Json.fromJson[MediaModel](input).get
      val content = contentModel.getDomain()
      val results = MediaService.create(content)
      results map(result =>Ok(Json.toJson(content)))
  }

  def getAll(contentId:String) = Action.async {
    request =>
      MediaService.getAllContentMedia(contentId) map (contents => Ok(Json.toJson(contents)))
  }

  def getContent(contentId:String,id: String) = Action.async {
    MediaService.getContentMediaById(contentId,id) map (content => Ok(Json.toJson(content)))
  }

  def update = Action.async(parse.json) {
    request =>
      val input = request.body
      val category = Json.fromJson[Media](input).get
      val results = MediaService.create(category)
      results map(result =>Ok(Json.toJson(category)))
  }

}
