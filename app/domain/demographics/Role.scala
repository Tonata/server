package domain.demographics

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/16.
 */
case class Role(
                 id: String,
                 name: String,
                 description: String,
                 state:String)

object Role {
  implicit val roleFmt = Json.format[Role]
}
