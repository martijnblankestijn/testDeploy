package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{Environment, Silhouette}
import com.mohiva.play.silhouette.impl.User
import com.mohiva.play.silhouette.impl.authenticators.JWTAuthenticator
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import play.api.i18n.MessagesApi

import scala.concurrent.Future

class ApplicationController @Inject() (
                                        val messagesApi: MessagesApi,
                                        val env: Environment[User, JWTAuthenticator],
                                        socialProviderRegistry: SocialProviderRegistry)
  extends Silhouette[User, JWTAuthenticator] {
  def user = SecuredAction.async { implicit request =>
    Future.successful(Ok(request.identity.toString))
//    Future.successful(Ok(Json.toJson(request.identity)))
  }
//class ApplicationController extends Controller {
//  def user = Action { request =>
//    Ok("It is me")
//  }
}
