package domain.util

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/06/14.
 */
case class Keys (id:String,value:String)

object Keys{
  implicit val keysFmt = Json.format[Keys]
}
