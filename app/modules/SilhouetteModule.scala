package modules

import com.google.inject.{AbstractModule, Provides}
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.services.AuthenticatorService
import com.mohiva.play.silhouette.api.util._
import com.mohiva.play.silhouette.api.{Environment, EventBus}
import com.mohiva.play.silhouette.impl.User
import com.mohiva.play.silhouette.impl.authenticators.{JWTAuthenticator, JWTAuthenticatorService, JWTAuthenticatorSettings}
import com.mohiva.play.silhouette.impl.daos.{CacheAuthenticatorDAO, DelegableAuthInfoDAO}
import com.mohiva.play.silhouette.impl.providers._
import com.mohiva.play.silhouette.impl.providers.oauth2.GoogleProvider
import com.mohiva.play.silhouette.impl.providers.oauth2.state.DummyStateProvider
import com.mohiva.play.silhouette.impl.repositories.DelegableAuthInfoRepository
import com.mohiva.play.silhouette.impl.util.{BCryptPasswordHasher, DefaultFingerprintGenerator, PlayCacheLayer, SecureRandomIDGenerator}
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._
import net.codingwell.scalaguice.ScalaModule
import play.api.Configuration
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.WSClient
import services.{OAuth2InfoDAO, UserService, UserServiceImpl}

class SilhouetteModule extends AbstractModule with ScalaModule {

  override def configure() {
    bind[UserService].to[UserServiceImpl]
    bind[DelegableAuthInfoDAO[OAuth2Info]].to[OAuth2InfoDAO]
    bind[CacheLayer].to[PlayCacheLayer]
    bind[OAuth2StateProvider].to[DummyStateProvider]
    bind[IDGenerator].toInstance(new SecureRandomIDGenerator())
    bind[PasswordHasher].toInstance(new BCryptPasswordHasher)
    bind[FingerprintGenerator].toInstance(new DefaultFingerprintGenerator(false))
    bind[EventBus].toInstance(EventBus())
    bind[Clock].toInstance(Clock())
  }

  @Provides
  def provideHTTPLayer(client: WSClient): HTTPLayer = new PlayHTTPLayer(client)

  @Provides
  def provideEnvironment(userService: UserService,
                         authenticatorService: AuthenticatorService[JWTAuthenticator],
                         eventBus: EventBus): Environment[User, JWTAuthenticator] =
    Environment[User, JWTAuthenticator](
      userService,
      authenticatorService,
      Seq(),
      eventBus
    )

  @Provides
  def provideSocialProviderRegistry(googleProvider: GoogleProvider): SocialProviderRegistry = SocialProviderRegistry(Seq(googleProvider))

  @Provides
  def provideAuthenticatorService(cacheLayer: CacheLayer,
                                  idGenerator: IDGenerator,
                                  configuration: Configuration,
                                  clock: Clock): AuthenticatorService[JWTAuthenticator] = {

    val config = configuration.underlying.as[JWTAuthenticatorSettings]("silhouette.authenticator")
    new JWTAuthenticatorService(config, Some(new CacheAuthenticatorDAO[JWTAuthenticator](cacheLayer)), idGenerator, clock)
  }

  @Provides
  def provideAuthInfoRepository(oauth2InfoDAO: DelegableAuthInfoDAO[OAuth2Info]): AuthInfoRepository = new DelegableAuthInfoRepository(oauth2InfoDAO)

  @Provides
  def provideGoogleProvider(httpLayer: HTTPLayer,
                            stateProvider: OAuth2StateProvider,
                            configuration: Configuration): GoogleProvider =
    new GoogleProvider(httpLayer, stateProvider, configuration.underlying.as[OAuth2Settings]("silhouette.google"))
}
