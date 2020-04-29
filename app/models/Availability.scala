package models

import io.swagger.annotations.{ApiModel, ApiModelProperty}
import play.api.libs.json.{Json, OFormat}

@ApiModel("Availability")
case class Availability(
                         @ApiModelProperty(value = "The name of the individual", required = true)
                         name: String,
                         @ApiModelProperty(required = true)
                         resource: Resource,
                         @ApiModelProperty(value = "The home location of the individual", required = false)
                         location: Option[String],
                         @ApiModelProperty(value = "The work location of the individual", required = true)
                         workLocation: String,
                         @ApiModelProperty(value = "The locations the individual is expected to be able to commute to", required = false)
                         reachableLocations: List[String] = List.empty,
                         @ApiModelProperty(required = true)
                         timesUnavailable: List[TimeUnavailable])

object Availability {
  implicit val formatAvailability: OFormat[Availability] = Json.format[Availability]
}

@ApiModel
case class Resource(
                     @ApiModelProperty(value = "The type of resource that the individual is eg. Surgeon")
                     resourceType: String,
                     @ApiModelProperty(value = "If the individual has been verified as being the stated resource")
                     verified: Boolean
                   )

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
