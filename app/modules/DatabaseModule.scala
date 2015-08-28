package modules

import javax.inject.Provider

import com.google.inject.{AbstractModule, Provides}
import com.typesafe.config.Config
import net.codingwell.scalaguice.ScalaModule
import play.api.Configuration
import play.api.inject.ApplicationLifecycle
import slick.jdbc.JdbcBackend

import scala.concurrent.Future

class DatabaseModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind(classOf[slick.jdbc.JdbcBackend.Database]).toProvider(classOf[DatabaseProvider])
    bind(classOf[models.UserDAO]).asEagerSingleton()

  }

  @Provides
  def provideDatabase( configuration: Config, lifecycle: ApplicationLifecycle) =
    new DatabaseProvider(configuration, lifecycle)

  @Provides
  def provideConfig(configuration: Configuration): Config = {
    configuration.underlying
  }
}

//@Singleton
class DatabaseProvider( configuration: Config, lifecycle: ApplicationLifecycle) extends Provider[slick.jdbc.JdbcBackend.Database] {

  private val db = slick.jdbc.JdbcBackend.Database.forConfig("db.default", configuration)

  lifecycle.addStopHook { () =>
    Future.successful(db.close())
  }

  override def get(): JdbcBackend.DatabaseDef = db
}