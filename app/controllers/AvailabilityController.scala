package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.json._
import scala.concurrent.Future

@Singleton
class AvailabilityController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def addAvailability() = Action(parse.json[Availability]) { implicit request =>
    Created(Json.toJson(request.body))
  }

  def fetchAvailability(location: String) = Action.async {
      Future.successful(Ok(Json.toJson(fakeAvailability)))
  }

  val fakeAvailability: List[Availability] = List(
    Availability(
      "Jane Doe",
      Resource("SHO", true),
      None,
      "S102JF",
      List("S102JF"),
      None,
      Some(List(TimeUnavailable(Time(14, 4, 2020, "08:00", "336:00"), "diagnosed"))),
      false
    )
  )
}

case class Availability(name: String,
                        resource: Resource,
                        location: Option[String],
                        workLocation: String,
                        reachableLocations: List[String],
                        timesAvailable: Option[List[Time]],
                        timesUnavailable: Option[List[TimeUnavailable]],
                        defaultAsAvailable: Boolean)

object Availability {
  implicit val formatAvailability: OFormat[Availability] = Json.format[Availability]
}

case class Resource(resourceType: String, verified: Boolean)

object Resource {
  implicit val formatAvailability: OFormat[Resource] = Json.format[Resource]
}

case class Time(date: Int, month: Int, year: Int, start: String, duration: String)

object Time {
  implicit val formatAvailability: OFormat[Time] = Json.format[Time]
}

case class TimeUnavailable(time: Time, reason: String)

object TimeUnavailable {
  implicit val formatAvailability: OFormat[TimeUnavailable] = Json.format[TimeUnavailable]
}