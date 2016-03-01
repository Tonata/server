package controllers

import play.api.libs.json.Json
import play.api.mvc._
import service.setup.{AccountSetupService, SchemaSetUpService}
import service.util.TokenService

class Application extends Controller {
  val token = TokenService()

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def setup = Action.async {
    val results = for {
      setup <- SchemaSetUpService.createCompanySchema
      roles <- AccountSetupService.createRoles
      user <- AccountSetupService.createAdmin
    } yield (setup)
    results map (result => {
      Ok(Json.toJson(result.isExhausted))
    })
  }

}
