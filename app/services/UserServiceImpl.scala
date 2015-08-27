package services

import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.User

import scala.concurrent.Future

class UserServiceImpl @Inject() () extends UserService {

  def retrieve(loginInfo: LoginInfo): Future[Option[User]] = {
    //    userDAO.find(loginInfo) {
    val user: User = User(loginInfo, None, None, None, None, None)
    Future.successful(Some(user))
  }
}


