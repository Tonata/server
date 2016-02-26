package service.content

import com.datastax.driver.core
import domain.content.Category
import repository.content.CategoryRepository

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/09.
 */
trait CategoryService {
  def getCategories: Future[Seq[Category]]
  def getCategory(id:String):Future[Option[Category]]
  def create(category:Category):Future[core.ResultSet]

}

object CategoryService {
  def apply():CategoryService = new CategoryServiceImpl

  private class CategoryServiceImpl extends CategoryService{
    override def getCategories: Future[Seq[Category]]= {
      println("This Method has been Called")
      CategoryRepository.getAllCategories
    }
    override def getCategory(id: String): Future[Option[Category]] = {
      CategoryRepository.getCategoryById(id)
    }

    override def create(category: Category):Future[core.ResultSet] = {
      CategoryRepository.save(category)
    }
  }
}


