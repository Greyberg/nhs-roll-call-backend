package db

import slick.lifted.ProvenShape

object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

trait Tables {
  val profile: slick.jdbc.JdbcProfile

  import profile.api._

  case class UserRow(userId: Long,
                     resourceType: String,
                     resourceVerified: Boolean,
                     workLocation: String,
                     defaultAsAvailable: Boolean)

  class UserTable(_tableTag: Tag) extends Table[UserRow](_tableTag, "users") {

    val userId: Rep[Long] = column[Long]("user_id", O.AutoInc, O.PrimaryKey)
    val resourceType: Rep[String] = column[String]("resource_type")
    val resourceVerified: Rep[Boolean] = column[Boolean]("resource_verified")
    val workLocation: Rep[String] = column[String]("work_location")
    val defaultAsAvailable: Rep[Boolean] = column[Boolean]("default_as_available")

    def * : ProvenShape[UserRow] =
      (userId, resourceType, resourceVerified, workLocation, defaultAsAvailable) <> (UserRow.tupled, UserRow.unapply)

  }

  lazy val UserTable = new TableQuery(tag => new UserTable(tag))

  case class UnavailabilityRow(
                                userId: Long,
                                startDate: Int,
                                startMonth: Int,
                                startYear: Int,
                                reason: String,
                                durationHours: String
                              )

  class UnavailabilityTable(_tableTag: Tag) extends Table[UnavailabilityRow](_tableTag, "unavailabilities") {

    val userId: Rep[Long] = column[Long]("user_id")
    val startDate: Rep[Int] = column[Int]("start_date")
    val startMonth: Rep[Int] = column[Int]("start_month")
    val startYear: Rep[Int] = column[Int]("start_year")
    val reason: Rep[String] = column[String]("reason")
    val durationHours: Rep[String] = column[String]("duration_hours")

    def * =
      (userId, startDate, startMonth, startYear, reason, durationHours) <> (UnavailabilityRow.tupled, UnavailabilityRow.unapply)
  }

  lazy val UnavailabilityTable = new TableQuery(tag => new UnavailabilityTable(tag))

}