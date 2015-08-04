package controllers

import model.{AddressModel, ContactModel}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.AddressService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/08/03.
 */
class AddressController extends Controller {
  val service = AddressService()

  def createContact = Action.async(parse.json) {
    request =>
      val input = request.body
      val contactModel = Json.fromJson[ContactModel](input).get
      val contact = contactModel.getDomain
      val results = service.createContact(contact)
      results map (result => Ok(Json.toJson(contact)))
  }

  def createAddress = Action.async(parse.json) {
    request =>
      val input = request.body
      val addressModel = Json.fromJson[AddressModel](input).get
      val address = addressModel.getDomain
      val results = service.createAddress(address)
      results map (result => Ok(Json.toJson(address)))
  }
  def getAddress(id: String) = Action.async {
    service.getAddress(id) map (address => Ok(Json.toJson(address)))
  }
  def getContact(id: String) = Action.async {
    service.getContact(id) map (contact => Ok(Json.toJson(contact)))
  }
}
