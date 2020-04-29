package db

import db.Tables._
import javax.inject.Inject
import models.Availability
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class UserDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider
                               ) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def saveUser(availability: Availability): Future[Long] = {

    val row = UserRow(
      0L,
      availability.name,
      availability.resource.resourceType,
      availability.resource.verified,
      availability.workLocation)


    val action = (UserTable returning UserTable.map(_.userId)) += row

    db.run(action)
  }

  def fetchUsers(workLocation: String): Future[Seq[UserRow]] = {
    val query = UserTable.filter(_.workLocation === workLocation)

    db.run(query.result)
  }

}

