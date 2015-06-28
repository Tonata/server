package service

import com.datastax.driver.core
import domain.Source
import repository.SourceRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/28.
 */
trait SourceService {
  def getSource(id:String):Future[Option[Source]]
  def getSources:Future[Seq[Source]]
  def create(source:Source):Future[core.ResultSet]
}
object SourceService{
  def apply():SourceService = new SourceServiceImpl

  private class SourceServiceImpl extends SourceService{
    override def getSource(id: String): Future[Option[Source]] = {
      SourceRepository.getSourceById(id)
    }

    override def getSources: Future[Seq[Source]] = {
      SourceRepository.getAllSources
    }

    override def create(source: Source): Future[core.ResultSet] = {
      SourceRepository.save(source)
    }
  }
}
