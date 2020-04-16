package models

import play.api.libs.json.{Json, OFormat}

//TODO - note changes in api doc
case class Availability(name: String,
                        resource: Resource,
                        location: Option[String],
                        workLocation: String,
                        reachableLocations: List[String],
                        timesUnavailable: List[TimeUnavailable],
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
