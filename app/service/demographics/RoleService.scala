package service.demographics

import com.datastax.driver.core.ResultSet
import domain.demographics.Role
import repository.demographics.RoleRepository
import service.Service

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/11/08.
 */
object RoleService extends Service{

  def saveOrUpdate(entity: Role): Future[ResultSet] = {
    RoleRepository.save(entity)
  }
  def get(id:String):Future[Option[Role]] ={
    RoleRepository.getRoleById(id)
  }

  def getAll:Future[Seq[Role]] ={
    RoleRepository.getRoles
  }
}
