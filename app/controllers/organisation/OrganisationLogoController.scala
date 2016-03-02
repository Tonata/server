package controllers.organisation

import domain.organisation.OrganisationLogo
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.organisation.OrganisationLogoServices
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2016/01/03.
 */
class OrganisationLogoController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[OrganisationLogo](input).get
      val results = OrganisationLogoServices.SaveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getOrganisationLogo(org:String, id: String) = Action.async {
    request =>
      OrganisationLogoServices.findDCompanyLogo(org,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllOrganisationLogos(org:String) = Action.async {
    request =>
      OrganisationLogoServices.findCompanyLogos(org) map (result =>
        Ok(Json.toJson(result)))
  }
}
