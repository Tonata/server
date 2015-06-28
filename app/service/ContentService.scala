package service


import domain.Content
import repository.ContentRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/06/09.
 */
trait ContentService {
  def create(content: Content)

  def getContent(id: String): Future[Option[Content]]

  def getAllContent: Future[Seq[Content]]

  def getContents(initValue: Int): Future[Iterator[Content]]

  def getContentsPerCategory(catId: String, initValue: Int): Future[Iterator[Content]]
}

object ContentService {
  def apply(): ContentService = new ContentServiceImpl

  private class ContentServiceImpl extends ContentService {
    override def create(content: Content): Unit = {
      ContentRepository.save(content)
    }

    override def getAllContent: Future[Seq[Content]] = {
      ContentRepository.getAllContents
    }

    override def getContent(id: String): Future[Option[Content]] = {
      ContentRepository.getContentById(id)
    }

    override def getContents(initValue: Int): Future[Iterator[Content]] = {
      ContentRepository.getContents(initValue)
    }

    override def getContentsPerCategory(catId: String, initValue: Int): Future[Iterator[Content]] = {
      ContentRepository.getContents(initValue) map (
        results => {
          results filter (content => content.category == catId)
        }
        )
    }
  }

}
