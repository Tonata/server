package service

import com.datastax.driver.core
import domain.EditedContent
import repository.EditedContentRepository

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
object EditedContentService {
  def create(content: EditedContent): Future[core.ResultSet] = {
    EditedContentRepository.save(content)
  }

  def getContent(id: String): Future[Option[EditedContent]] ={
    EditedContentRepository.getContentById(id)

  }

  def getAllContent: Future[Seq[EditedContent]] = {
    EditedContentRepository.getAllContents
  }

  def getRawContent(startValue: Int): Future[Iterator[EditedContent]] = {
    EditedContentRepository.getContents(startValue)
  }

}
