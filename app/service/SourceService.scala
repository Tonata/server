package service

import domain.Source

/**
 * Created by hashcode on 2015/06/28.
 */
trait SourceService {
  def getSource(id:String):Source
  def getSoures:List[Source]
  def create(source:Source)
  def update(source:Source)
}

object SourceService{
  def apply():SourceService = new SourceServiceImpl

  private class SourceServiceImpl extends SourceService{
    override def getSource(id: String): Source = ???

    override def update(source: Source): Unit = ???

    override def getSoures: List[Source] = ???

    override def create(source: Source): Unit = ???
  }
}
