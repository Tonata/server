package model

import java.util.{UUID, Date}

import conf.util.Util
import domain.{Media, Content}
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/02/11.
  */
case class MediaModel(contentId: String,
                      description: String,
                      url: String,
                      mime: String,
                      date: Date,
                      state:String)
{
  def getDomain():Media=MediaModel.domain(this)
}

object MediaModel {
  implicit val medialFmt = Json.format[MediaModel]
  def domain(model: MediaModel) = {
    Media(
      model.contentId,
      Util.md5Hash(UUID.randomUUID().toString()),
      model.description,
      model.url,
      model.mime,
      model.date,
      model.state)
  }
}