package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.libs.json._

@Singleton
class AvailabilityController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def addAvailability() = Action(parse.json[Availability]) { implicit request =>
    Created(Json.toJson(request.body))
  }
}

case class Availability(name: String,
                        resource: Resource,
                        location: String,
                        reachableLocations: List[String],
                        timesAvailable: Option[List[Times]],
                        timesUnavailable: Option[List[Times]],
                        defaultAsAvailable: Boolean)

object Availability {
  implicit val formatAvailability: OFormat[Availability] = Json.format[Availability]
}

case class Resource(resourceType: String, verified: Boolean)

object Resource {
  implicit val formatAvailability: OFormat[Resource] = Json.format[Resource]
}

case class Times(date: Int, month: Int, year: Int, start: String, duration: String)

object Times {
  implicit val formatAvailability: OFormat[Times] = Json.format[Times]
}