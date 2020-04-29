package controllers

import io.swagger.annotations.{Api, ApiOperation, ApiParam, ApiResponse, ApiResponses}
import javax.inject._
import models._
import play.api.mvc._
import play.api.libs.json.Json
import services.AvailabilityService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

@Singleton
@Api(value = "/availability")
class AvailabilityController @Inject()(
                                        val controllerComponents: ControllerComponents,
                                        availabilityService: AvailabilityService)(
                                      implicit ec: ExecutionContext
) extends BaseController {

  def addAvailability(): Action[Availability] = Action(parse.json[Availability]).async { implicit request =>
    availabilityService.saveUserWithUnavailability(request.body).map( _ => Ok)
  }

  @ApiOperation(
    nickname = "fetchAvailability",
    value = "Fetch unavailabilities of given location",
    response = classOf[List[Availability]],
    httpMethod = "GET",
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid location supplied")
  ))
  def fetchAvailability(@ApiParam(value = "Location of unavailabilities") location: String): Action[AnyContent] = Action.async {
      Future.successful(Ok(Json.toJson(fakeAvailability)))
  }

  val fakeAvailability: List[Availability] = List(
    Availability(
      "Jane Doe",
      Resource("SHO", true),
      None,
      "S102JF",
      List("S102JF"),
      List(TimeUnavailable(Time(14, 4, 2020, "08:00", "336:00"), "diagnosed"))
    )
  )
}