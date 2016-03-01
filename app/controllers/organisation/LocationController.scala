package controllers.organisation

import domain.organisation.Location
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.organisation.LocationService

/**
  * Created by hashcode on 2016/02/25.
  */
class LocationController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Location](input).get
      val results = LocationService.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(org:String, id: String) = Action.async {
    request =>
      LocationService.findById(org,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllLocations(org:String) = Action.async {
    request =>
      LocationService.findAll(org) map (result =>
        Ok(Json.toJson(result)))
  }

}
