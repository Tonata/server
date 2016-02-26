package service

import domain.content.Category
import org.scalatest.{GivenWhenThen, FeatureSpec}
import service.content.CategoryService

/**
 * Created by hashcode on 2015/07/27.
 */
class CateGoryServiceTest extends FeatureSpec with GivenWhenThen{
  feature(" Category Service"){
    scenario(" Test "){
      Given(" Category Object ")
      val cat1 = Category("1","Health1","Health Care Category 1")
      val cat2 = Category("2","Health2","Health Care Category 2")
      val cat3 = Category("3","Health3","Health Care Category 3")

      CategoryService().create(cat1)
      CategoryService().create(cat2)
      CategoryService().create(cat3)

    }

  }

}
