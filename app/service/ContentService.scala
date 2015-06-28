package service

import domain.Content

/**
 * Created by hashcode on 2015/06/09.
 */
trait ContentService {
  def create(content:Content)
  def getContent(id:String):Content
  def updateContent(content:Content)
  def getAllContent:List[Content]
  def getContents(initValue:Int):List[Content]
  def getContentsPerCategory(catId:String,initValue:Int):List[Content]
}

object ContentService {
  def apply():ContentService = new ContentServiceImpl

  private class ContentServiceImpl extends ContentService {
    override def create(content: Content): Unit = ???

    override def getAllContent: List[Content] = ???

    override def getContent(id: String): Content = ???

    override def getContents(initValue: Int): List[Content] = ???

    override def getContentsPerCategory(catId: String, initValue: Int): List[Content] = ???

    override def updateContent(content: Content): Unit = ???
  }
}
