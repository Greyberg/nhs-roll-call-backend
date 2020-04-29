package services

import db._
import javax.inject.Inject
import models._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import db.Tables._

class AvailabilityService @Inject()(userDao: UserDao, availabilityDao: AvailabilityDao) {

  def fetchUnavailabilities(location: String): Future[Seq[Availability]] = {
    for {
      users <- userDao.fetchUsers(location)
      availabilities <- Future.sequence(users.map(makeAvailability))
    } yield availabilities
  }

  def makeAvailability(user: UserRow): Future[Availability] = {
    //Get all the availabilities for the user, then munge everything into one availability object
    for {
      availabilities <- availabilityDao.fetchUnavailabilities(user.userId)
    } yield Availability(
      user.userName,
      Resource(user.resourceType, user.resourceVerified),
      None,
      user.workLocation,
      List.empty,
      availabilities.map(toTimeUnavailable).toList
    )
  }

  def toTimeUnavailable(unavailability: UnavailabilityRow): TimeUnavailable = {

    TimeUnavailable(
      Time(
        unavailability.startDate,
        unavailability.startMonth,
        unavailability.startYear,
        unavailability.durationDays
      ),
      unavailability.reason)
  }


  def saveUserWithUnavailability(availability: Availability): Future[Int] = {
      val futureUserId = userDao.saveUser(availability)

    futureUserId.flatMap{ userId =>
      availabilityDao.saveUnavailabilities(userId, availability).map(thing => thing.length)
    }
  }
}
