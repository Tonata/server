package service


import com.datastax.driver.core
import domain.Content
import repository.ContentRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/09.
 */
trait ContentService {

  def create(content: Content): Future[core.ResultSet]

  def getContent(id: String): Future[Option[Content]]

  def getAllContent: Future[Seq[Content]]

  def getRawContent(initValue: Int): Future[Iterator[Content]]


  def relatedItems(contentId: String): Future[Seq[Content]]

  def getContents(initValue: Int): Future[Iterator[Content]]

  def getContentsByCateGory(initValue: Int, cateId:String): Future[Iterator[Content]]

  def getContentsPerCategory(catId: String, initValue: Int): Future[Iterator[Content]]

  def isInEditOrPublished(id:String):Future[Seq[Boolean]]

  def isPublished(id:String):Future[Seq[Boolean]]

  def getContentByType(typeName:String,initValue:Int):Future[Iterator[Content]]
}

object ContentService {
  def apply(): ContentService = new ContentServiceImpl

  private class ContentServiceImpl extends ContentService {
    override def create(content: Content): Future[core.ResultSet] = {
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

    override def relatedItems(contentId: String): Future[Seq[Content]] = {
      val contentCategory = getContent(contentId) map (content => content map (cat => cat.category))
      getAllContent map (contents => contents filter (
        content => {
          val contentId = contentCategory map (cat => cat map (p => p))
          content == contentId
        } ))
    }

    override def getContentsByCateGory(initValue: Int, cateId: String): Future[Iterator[Content]] = {
      getContents(initValue) map( contents => contents filter(content => content.category==cateId))
    }

    override def isInEditOrPublished(id: String): Future[Seq[Boolean]] = {
      val published = getAllContent map(content=> content )
          published map(conts => conts map( v => v.source==id))

    }

    override def isPublished(id: String): Future[Seq[Boolean]] ={
      val published = getAllContent map(content=> content filter(cont=> cont.contentType.equalsIgnoreCase("Published")))

      published map( conts => conts map( v => v.source==id))
    }

    override def getContentByType(typeName: String, initValue: Int): Future[Iterator[Content]] = {
      getContents(initValue) map ( content => content filter(cont=> cont.source==typeName))
    }

     override def getRawContent(initValue: Int): Future[Iterator[Content]] = {
      getContents(initValue) map( contents => contents filter(content =>
        ((content.contentType=="RAW") & (content.source=="MOBILE"))))
    }
  }
}
