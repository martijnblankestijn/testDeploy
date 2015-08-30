package controllers

import javax.inject.Inject

import models.{UserDAO, Users}
import play.api.mvc._
import slick.lifted.TableQuery
import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(userdao: UserDAO) extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def todo = Action.async {
    val schema = TableQuery[Users].schema
    schema.create.statements.foreach(println)
    userdao.all.map(users => Ok(views.html.todo(users)))
  }
}
