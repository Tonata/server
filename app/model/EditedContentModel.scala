package model

import java.util.{UUID, Date}

import conf.util.Util
import domain.Content
import domain.content.EditedContent
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/02/11.
  */
case class EditedContentModel(dateCreated: Date,
                              creator: String,
                              source: String,
                              category: String,
                              title: String,
                              content: String,
                              contentType: String,
                              status: String,
                              state: String){
  def getDomain():EditedContent=EditedContentModel.domain(this)
}

object EditedContentModel {
  implicit val editedContentModelFmt = Json.format[EditedContentModel]
  def domain(model: EditedContentModel) = {
    EditedContent(
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