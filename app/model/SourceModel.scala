package model

import java.util.UUID

import conf.util.Util
import domain.content.Source
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/06/25.
 */
case class SourceModel(
                        name: String,
                        description:String
                        ){
  def getDomain():Source=SourceModel.domain(this)
}

object SourceModel {
  implicit val contentTypeFmt = Json.format[SourceModel]

  def domain(model: SourceModel) = {
    Source(
      Util.md5Hash(UUID.randomUUID().toString()),
      model.name,
      model.description
    )
  }
}
