package db

import slick.lifted.ProvenShape

object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

trait Tables {
  val profile: slick.jdbc.JdbcProfile

  import profile.api._

  case class UserRow(userId: Long,
                     userName: String,
                     resourceType: String,
                     resourceVerified: Boolean,
                     workLocation: String)

  class UserTable(_tableTag: Tag) extends Table[UserRow](_tableTag, "users") {

    val userId: Rep[Long] = column[Long]("user_id", O.AutoInc, O.PrimaryKey)
    val userName: Rep[String] = column[String]("user_name")
    val resourceType: Rep[String] = column[String]("resource_type")
    val resourceVerified: Rep[Boolean] = column[Boolean]("resource_verified")
    val workLocation: Rep[String] = column[String]("work_location")

    def * : ProvenShape[UserRow] =
      (userId, userName, resourceType, resourceVerified, workLocation) <> (UserRow.tupled, UserRow.unapply)

  }

  lazy val UserTable = new TableQuery(tag => new UserTable(tag))

  case class UnavailabilityRow(
                                userId: Long,
                                startDate: Int,
                                startMonth: Int,
                                startYear: Int,
                                reason: String,
                                durationDays: Int
                              )

  class UnavailabilityTable(_tableTag: Tag) extends Table[UnavailabilityRow](_tableTag, "unavailabilities") {

    val userId: Rep[Long] = column[Long]("user_id")
    val startDate: Rep[Int] = column[Int]("start_date")
    val startMonth: Rep[Int] = column[Int]("start_month")
    val startYear: Rep[Int] = column[Int]("start_year")
    val reason: Rep[String] = column[String]("reason")
    val durationDays: Rep[Int] = column[Int]("duration_days")

    def * =
      (userId, startDate, startMonth, startYear, reason, durationDays) <> (UnavailabilityRow.tupled, UnavailabilityRow.unapply)
  }

  lazy val UnavailabilityTable = new TableQuery(tag => new UnavailabilityTable(tag))

}