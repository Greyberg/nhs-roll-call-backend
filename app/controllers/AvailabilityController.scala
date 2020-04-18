package controllers

import javax.inject._
import models._
import play.api.mvc._
import play.api.libs.json.Json
import services.AvailabilityService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

@Singleton
class AvailabilityController @Inject()(
                                        val controllerComponents: ControllerComponents,
                                        availabilityService: AvailabilityService)(
                                      implicit ec: ExecutionContext
) extends BaseController {

  def addAvailability(): Action[Availability] = Action(parse.json[Availability]).async { implicit request =>
    availabilityService.saveUserWithUnavailability(request.body).map( _ => Ok)
  }

  def fetchAvailability(location: String): Action[AnyContent] = Action.async {
      Future.successful(Ok(Json.toJson(fakeAvailability)))
  }

  val fakeAvailability: List[Availability] = List(
    Availability(
      "Jane Doe",
      Resource("SHO", true),
      None,
      "S102JF",
      List("S102JF"),
      List(TimeUnavailable(Time(14, 4, 2020, "08:00", "336:00"), "diagnosed")),
      false
    )
  )
}