package service.content

import com.datastax.driver.core
import domain.content.EditedContent
import repository.content.EditedContentRepository

import scala.concurrent.Future


/**
  * Created by hashcode on 2016/02/11.
  */
object EditedContentService {
  def create(content: EditedContent): Future[core.ResultSet] = {
    EditedContentRepository.save(content)
  }

  def getContentById(org:String, id: String): Future[Option[EditedContent]] ={
    EditedContentRepository.getContentById(org,id)

  }

  def getAllContents(org:String): Future[Seq[EditedContent]] = {
    EditedContentRepository.getAllContents(org)
  }

  def getContents(org:String,startValue: Int): Future[Iterator[EditedContent]] = {
    EditedContentRepository.getContents(org,startValue)
  }

}

