package controllers

import javax.inject._
import models._
import play.api.mvc._
import play.api.libs.json.Json

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