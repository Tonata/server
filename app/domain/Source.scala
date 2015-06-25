package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/06/25.
 */
case class Source (
                    id:String,
                    name:String,
                    description:String)

object Source {
  implicit val sourceFmt = Json.format[Source]

}
