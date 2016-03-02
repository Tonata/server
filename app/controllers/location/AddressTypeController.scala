package controllers.location

import domain.location.AddressType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.location.AddressTypeService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class AddressTypeController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AddressType](input).get
      val results = AddressTypeService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      AddressTypeService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      AddressTypeService.getAll map (result =>
        Ok(Json.toJson(result)))
  }

}
