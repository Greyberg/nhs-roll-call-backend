package services

import db._
import javax.inject.Inject
import models._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AvailabilityService @Inject()(userDao: UserDao, availabilityDao: AvailabilityDao) {

  def saveUserWithUnavailability(availability: Availability): Future[Int] = {
      val futureUserId = userDao.saveUser(availability)

    futureUserId.flatMap{ userId =>
      availabilityDao.saveUnavailabilities(userId, availability).map(thing => thing.length)
    }
  }
}
