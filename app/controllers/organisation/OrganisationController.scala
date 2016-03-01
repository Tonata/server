package controllers.organisation

import domain.organisation.Organisation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.organisation.OrganisationService

/**
  * Created by hashcode on 2016/02/28.
  */
class OrganisationController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Organisation](input).get
      val results = OrganisationService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      OrganisationService.getById(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      OrganisationService.getAll map (result =>
        Ok(Json.toJson(result)))
  }
}
