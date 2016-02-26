package service.content

import com.datastax.driver.core
import domain.content.Source
import repository.content.SourceRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/28.
 */
trait SourceService {
  def getSource(org:String,id: String):Future[Option[Source]]
  def getSources(org:String):Future[Seq[Source]]
  def create(source:Source):Future[core.ResultSet]
}
object SourceService{
  def apply():SourceService = new SourceServiceImpl

  private class SourceServiceImpl extends SourceService{
    override def getSource(org:String,id: String): Future[Option[Source]] = {
      SourceRepository.getSourceById(org,id)
    }

    override def getSources(org:String): Future[Seq[Source]] = {
      SourceRepository.getAllSources(org)
    }

    override def create(source: Source): Future[core.ResultSet] = {
      SourceRepository.save(source)
    }
  }
}
