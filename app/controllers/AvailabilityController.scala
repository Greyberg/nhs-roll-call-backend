package controllers

import io.swagger.annotations._
import javax.inject._
import models._
import play.api.libs.json.Json
import play.api.mvc._
import services.AvailabilityService

import scala.concurrent.ExecutionContext

@Singleton
@Api(value = "/availability")
class AvailabilityController @Inject()(
                                        val controllerComponents: ControllerComponents,
                                        availabilityService: AvailabilityService)(
                                      implicit ec: ExecutionContext
) extends BaseController {

  //TODO - document this endpoint
  def addAvailability(): Action[Availability] = Action(parse.json[Availability]).async { implicit request =>
    availabilityService.saveUserWithUnavailability(request.body).map( _ => Ok)
  }

  //TODO - return 400 with message in case of invalid location supplied
  @ApiOperation(
    nickname = "fetchAvailability",
    value = "Fetch unavailabilities of given location",
    response = classOf[List[Availability]],
    httpMethod = "GET",
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid location supplied")
  ))
  def fetchAvailability(@ApiParam(value = "Location of unavailabilities") location: String): Action[AnyContent] = {
    Action.async {
      availabilityService.fetchUnavailabilities(location).map { result =>
        Ok(Json.toJson(result))
      }
    }
  }
}