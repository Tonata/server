package service.storage

import com.datastax.driver.core.ResultSet
import domain.storage.StorageUrl
import repository.storage.StorageUrlRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/05.
 */
object StorageUrlServices extends Service {

  def saveOrUpdate(link: StorageUrl): Future[ResultSet] = {
    StorageUrlRepository.save(link)
  }

  def getAllLinks: Future[Seq[StorageUrl]] = {
    StorageUrlRepository.getAllLinks
  }

  def getLinkById(id: String): Future[Option[StorageUrl]] = {
    StorageUrlRepository.getLinkById(id)
  }

}
