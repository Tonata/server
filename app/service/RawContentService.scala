package service

import com.datastax.driver.core
import domain.RawContent
import repository.RawContentRepository

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
object RawContentService {
  def create(content: RawContent): Future[core.ResultSet] = {
    RawContentRepository.save(content)
  }

  def getContent(id: String): Future[Option[RawContent]] = {
    RawContentRepository.getContentById(id)

  }

  def getAllContent: Future[Seq[RawContent]] = {
    RawContentRepository.getAllContents
  }

  def getRawContent(startValue: Int): Future[Iterator[RawContent]] = {
    RawContentRepository.getContents(startValue)
  }

}
