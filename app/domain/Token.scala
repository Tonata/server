package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/06/07.
 */
case class Token(id: String,
            token: String
             )

object Token  {
  implicit val tokenFmt = Json.format[Address]

}