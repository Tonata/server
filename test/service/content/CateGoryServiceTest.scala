package service.content

import domain.content.Category
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatestplus.play.PlaySpec

/**
 * Created by hashcode on 2015/07/27.
 */
class CateGoryServiceTest extends PlaySpec{

"CategoryService # Apply" should{
  "be true when the Category id exists" in {

    val  cate1 = Category("1","Health1","Health Care Category 2")
   //val newcate = Category("1","Health1","Health Care Category 1")

    val categoryService = CategoryService().create(cate1)




  }
}
  /*feature(" Category Service"){
    scenario(" Test "){
      Given(" Category Object ")
      val cat1 = Category("1","Health1","Health Care Category 1")
      val cat2 = Category("2","Health2","Health Care Category 2")
      val cat3 = Category("3","Health3","Health Care Category 3")

      CategoryService().create(cat1)
      CategoryService().create(cat2)
      CategoryService().create(cat3)

    }*/



}
