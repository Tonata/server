package service

import com.datastax.driver.core
import com.datastax.driver.core.ResultSet
import domain.{Contact, Address, Category}
import repository.{ContactRepository, AddressRepository}

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
    override def getAddress(id: String): Future[Option[Address]] = {
      AddressRepository.getAddressId(id)
    }

    override def createAddress(address: Address): Future[ResultSet] = {
      AddressRepository.save(address)
    }

    override def getContact(id: String): Future[Option[Contact]] = {
      ContactRepository.getContactById(id)
    }

    override def createContact(contact: Contact): Future[ResultSet] = {
      ContactRepository.save(contact)
    }
  }

}
