package model

import domain.Role
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class RoleModel(
                roleName: String,
                description: String
                 ){
  def getDomain():Role=RoleModel.domain(this)
}

object RoleModel {
  implicit val roleFmt = Json.format[RoleModel]
  def domain(model: RoleModel) = {
    Role(
      model.roleName,
      model.description
    )
  }
}