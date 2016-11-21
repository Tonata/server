package domain.demographics

import play.api.libs.json.Json

/**
  * Created by user42 on 2016/09/08.
  */
case class MartialStatus(id:String,name:String,state:String)


object MartialStatus{
  implicit val maritalStatusFmt = Json.format[MartialStatus]

}
