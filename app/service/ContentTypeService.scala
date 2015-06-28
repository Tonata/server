package service

import com.datastax.driver.core
import domain.ContentType
import repository.ContentTypeRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/28.
 */
trait ContentTypeService {
  def getContentType(id:String):Future[Option[ContentType]]
  def create(contentType:ContentType):Future[core.ResultSet]
  def getContentTypes:Future[Seq[ContentType]]
}

object ContentTypeService{
  def apply():ContentTypeService = new ContentTypeServiceImpl

  private class ContentTypeServiceImpl extends ContentTypeService {
    override def getContentType(id: String): Future[Option[ContentType]] = {
      ContentTypeRepository.getContentTypeById(id)
    }


    override def getContentTypes: Future[Seq[ContentType]] = {
      ContentTypeRepository.getAllContentTypes
    }

    override def create(contentType: ContentType): Future[core.ResultSet] = {
      ContentTypeRepository.save(contentType)
    }
  }
}