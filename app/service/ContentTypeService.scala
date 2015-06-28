package service

import domain.ContentType

/**
 * Created by hashcode on 2015/06/28.
 */
trait ContentTypeService {
  def getContetType(id:String):ContentType
  def create(contentType:ContentType)
  def getContentTypes:List[ContentType]
  def update(contentType:ContentType)
}

object ContentTypeService{
  def apply():ContentTypeService = new ContentTypeServiceImpl

  private class ContentTypeServiceImpl extends ContentTypeService {
    override def getContetType(id: String): ContentType = ???

    override def update(contentType: ContentType): Unit = ???

    override def getContentTypes: List[ContentType] = ???

    override def create(contentType: ContentType): Unit = ???
  }
}