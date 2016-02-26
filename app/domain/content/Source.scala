package domain.content

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/06/25.
 */
case class Source ( org: String,
                    id:String,
                    name:String,
                    description:String)

object Source {
  implicit val sourceFmt = Json.format[Source]

}
