package service.content

import com.datastax.driver.core
import domain.content.{EditedContent, RawContent}
import repository.content.{EditedContentRepository, RawContentRepository}

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
object RawContentService {
  def create(content: RawContent): Future[core.ResultSet] = {
    RawContentRepository.save(content)
  }

  def getContentById(org:String, id: String): Future[Option[RawContent]] ={
    RawContentRepository.getContentById(org,id)

  }

  def getAllContents(org:String): Future[Seq[RawContent]] = {
    RawContentRepository.getAllContents(org)
  }

  def getContents(org:String,startValue: Int): Future[Iterator[RawContent]] = {
    RawContentRepository.getContents(org,startValue)
  }
}
