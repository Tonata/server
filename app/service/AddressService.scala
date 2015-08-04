package service

import com.datastax.driver.core
import com.datastax.driver.core.ResultSet
import domain.{Contact, Address, Category}

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/08/03.
 */
trait AddressService {
  def getAddress(id:String):Future[Option[Address]]
  def createAddress(address:Address):Future[core.ResultSet]
  def getContact(id:String):Future[Option[Contact]]
  def createContact(contact:Contact):Future[core.ResultSet]
}

object AddressService{
  def apply():AddressService = new AddressServiceImpl
  private class AddressServiceImpl extends AddressService{
    override def getAddress(id: String): Future[Option[Address]] = ???

    override def createAddress(address: Address): Future[ResultSet] = ???

    override def getContact(id: String): Future[Option[Contact]] = ???

    override def createContact(contact: Contact): Future[ResultSet] = ???
  }

}
