package service.person

import com.datastax.driver.core.ResultSet

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object PeopleService extends Service{

  def saveOrUpdate(entity: Person): Future[ResultSet] = {
    PersonRepository.save(entity)
  }
  def getPeople(id:String):Future[Seq[Person]] ={
    PersonRepository.findPeople(id)
  }

  def getPerson(company:String,id:String):Future[Option[Person]] ={
    PersonRepository.findPerson(company,id)
  }
  def getPersonByEmail(email:String):Future[Option[Person]] = {
    UsersRepository.findByEmail(email)
  }

}
