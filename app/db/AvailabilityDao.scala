package db

import db.Tables._
import javax.inject.Inject
import models.Availability
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AvailabilityDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider
                               ) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def saveUnavailabilities(userId: Long, availability: Availability): Future[Seq[Int]] = {

    val rows: Seq[UnavailabilityRow] = availability.timesUnavailable.map { time =>
      UnavailabilityRow(
        userId,
        time.time.date,
        time.time.month,
        time.time.year,
        time.reason,
        time.time.duration)
    }

    val seq = rows.map { row =>
      val action = UnavailabilityTable += row
      db.run(action)
    }

    Future.sequence(seq)
  }

  def fetchUnavailabilities(userId: Long): Future[Seq[UnavailabilityRow]] = {
    val query = UnavailabilityTable.filter(_.userId === userId)

    db.run(query.result)
  }

}
