package service

import com.datastax.driver.core
import domain.PublishedContent
import repository.PublishedContentRepository

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
object PublishedContentService {
  def create(content: PublishedContent): Future[core.ResultSet] = {
    PublishedContentRepository.save(content)
  }

  def getContent(id: String): Future[Option[PublishedContent]] = {
    PublishedContentRepository.getContentById(id)

  }

  def getAllContent: Future[Seq[PublishedContent]] = {
    PublishedContentRepository.getAllContents
  }

  def getRawContent(startValue: Int): Future[Iterator[PublishedContent]] = {
    PublishedContentRepository.getContents(startValue)
  }

}
