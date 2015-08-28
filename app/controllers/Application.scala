package controllers

import javax.inject.Inject

import models.UserDAO
import play.api.mvc._
import play.twirl.api.HtmlFormat

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(userdao: UserDAO) extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def todo = Action.async {
    userdao.all.map(users => Ok(views.html.todo(users)))
  }
}
