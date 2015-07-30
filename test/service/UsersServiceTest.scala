package service

import domain.User
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
 * Created by hashcode on 2015/07/28.
 */
class UsersServiceTest extends FeatureSpec with GivenWhenThen{

  feature(" Adding a User "){
    scenario(" Created Person Object "){
      Given(" A User")
      val user = User("1","other","first","lastname","username",true,"test13",Set("1234","777"),List("1234","3333"),List("1234"))

      val userService = UserService()
      userService.create(user)
    }
  }
}
