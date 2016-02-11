package model

import java.util.{UUID, Date}

import conf.util.Util
import domain.{Content, RawContent, EditedContent}
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/02/11.
  */
case class RawContentModel(dateCreated: Date,
                           creator: String,
                           source: String,
                           category: String,
                           title: String,
                           content: String,
                           contentType: String,
                           status: String,
                           state: String)
{
  def getDomain():RawContent=RawContentModel.domain(this)
}

object RawContentModel {
  implicit val rawContentModelFmt = Json.format[RawContentModel]
  def domain(model: RawContentModel) = {
    RawContent(
      Util.md5Hash(UUID.randomUUID().toString()),
      model.dateCreated,
      model.creator,
      model.source,
      model.category,
      model.title,
      model.content,
      model.contentType,
      model.status,
      model.state)
  }

}