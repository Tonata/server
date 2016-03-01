package controllers.storage

import domain.storage.StorageUrl
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.storage.StorageUrlServices

/**
 * Created by hashcode on 2016/01/05.
 */
class StorageUrlController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[StorageUrl](input).get
      val results = StorageUrlServices.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      StorageUrlServices.getLinkById(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllLinks = Action.async {
    request =>
      StorageUrlServices.getAllLinks map (result =>
        Ok(Json.toJson(result)))
  }
}
