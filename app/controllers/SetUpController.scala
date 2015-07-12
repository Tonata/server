package controllers

import play.api.mvc.{Action, Controller}
import repository._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/16.
 */
class SetUpController extends Controller{



  def dbsetup = Action{
    val results = for {
      source <- SourceRepository.createTable()
      address <- AddressRepository.createTable()
      category <- CategoryRepository.createTable()
      contact <- ContactRepository.createTable()
      content <-ContentRepository.createTable()
      conteType <-ContentTypeRepository.createTable()
      keysType <-KeysRepository.createTable()
      role <- RoleRepository.createTable()
      token <-TokenRepository.createTable()
      user <-UserRepository.createTable()
    } yield (source)
    Ok("")
  }
}
