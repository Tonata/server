package service

import com.datastax.driver.core.ResultSet
import domain.Media
import repository.MediaRepository

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/02/11.
  */
object MediaService {
  def create(media: Media): Future[ResultSet] = {
    MediaRepository.save(media)
  }

  def getContentMediaById(contentId: String, id: String): Future[Option[Media]] = {
    MediaRepository.getContentMediaById(contentId, id)

  }

  def getAllContentMedia(contentId: String): Future[Seq[Media]] = {
    MediaRepository.getAllContentMedia(contentId)
  }


}
