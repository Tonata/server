package controllers

import play.api.mvc.{Action, Controller}
import repository._

/**
 * Created by hashcode on 2015/06/16.
 */
class SetUpController extends Controller{

  val add = AddressRepository.create

  def dbsetup = Action{
    val results = for {
      source <- SourceRepository.createTable()
      address <- AddressRepository.create
      category <- CategoryRepository.create
      contact <- ContactRepository.create
      content <-ContentRepository.create
      conteType <-ContentTypeRepository.create
      keysType <-KeysRepository.create
      role <- RoleRepository.create
      token <-TokenResposiory.create
      user <-UserRepository.create
    } yield (source)
    Ok("")
  }
}
