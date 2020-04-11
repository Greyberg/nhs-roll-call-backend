package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json

@Singleton
class StatusController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def status() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("status" -> "OK"))
  }
}
