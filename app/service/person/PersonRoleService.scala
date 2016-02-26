package service.person

import com.datastax.driver.core.ResultSet
import domain.person.PersonRole
import repository.person.PersonRoleRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object PersonRoleService extends Service {
  def saveOrUpdate(entity: PersonRole): Future[ResultSet] = {
    PersonRoleRepository.save(entity)
  }

  def get(id: String, roleId: String): Future[Option[PersonRole]] = {
    PersonRoleRepository.findRole(id, roleId)
  }

  def getAllRoles(personId: String): Future[Seq[PersonRole]] = {
    PersonRoleRepository.findRolesById(personId)
  }

}
