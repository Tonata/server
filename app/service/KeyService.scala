package service

import java.util.UUID

import conf.util.Util
import domain.Keys
import repository.KeysRepository

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
 * Created by hashcode on 2015/06/14.
 */
trait KeyService {
  def createKey()
  def getKey():Future[String]
}

object KeyService{
  def apply(): KeyService = new KeyServiceImpl
  private class KeyServiceImpl extends KeyService{
    override def createKey(): Unit = {
      getKey() onComplete {
        case Success (key) => {
          KeysRepository.delete(key)
          KeysRepository.save(Keys(Util.md5Hash(UUID.randomUUID().toString),""))
        }
        case Failure =>  KeysRepository.save(Keys(Util.md5Hash(UUID.randomUUID().toString),""))
      }
    }

    override def getKey(): Future[String] = {
      for(
        keys <-  KeysRepository.getAllkeys map (values => values);
        key  <- keys;
        keyId <- key.id
      )yield keyId
    }
  }
}
