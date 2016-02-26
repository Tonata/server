package service.content

import com.datastax.driver.core
import domain.content.{EditedContent, PublishedContent}
import repository.content.{EditedContentRepository, PublishedContentRepository}

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
object PublishedContentService {
  def create(content: PublishedContent): Future[core.ResultSet] = {
    PublishedContentRepository.save(content)
  }

  def getContentById(org:String, id: String): Future[Option[PublishedContent]] ={
    PublishedContentRepository.getContentById(org,id)

  }

  def getAllContents(org:String): Future[Seq[PublishedContent]] = {
    PublishedContentRepository.getAllContents(org)
  }

  def getContents(org:String,startValue: Int): Future[Iterator[PublishedContent]] = {
    PublishedContentRepository.getContents(org,startValue)
  }
}
