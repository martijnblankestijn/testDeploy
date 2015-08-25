name := """testdeploy"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala,SbtWeb)

scalaVersion := "2.11.6"


scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")


libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
 // specs2 % Test,
"com.typesafe.slick"   %% "slick"                      % "3.0.2",
"org.xerial"            % "sqlite-jdbc"                % "3.7.2"
// -------------------------------------------------------------------------
// Web Jars
//"org.webjars"           % "bootstrap"                  % "3.3.2-2",
//"org.webjars"           % "modernizr"                  % "2.8.3",
//"org.webjars"           % "html5shiv"                  % "3.7.2",
//"org.webjars.bower"     % "traceur"                    % "0.0.89",
//"org.webjars.bower"     % "traceur-runtime"            % "0.0.89",
//"org.webjars"           % "font-awesome"               % "4.3.0",
//"org.webjars"           % "jquery"                     % "1.11.2",
//"org.webjars"           % "log4javascript"             % "1.4.10",
//"org.webjars"           % "lodash"                     % "3.1.0",
//"org.webjars"           % "momentjs"                   % "2.9.0",
// Angular-specific.
//
// NOTE: Even though ng-tags-input claims not to support AngularJS 1.3 yet,
// it seems to work fine.
//"org.webjars"           % "angularjs"                  % "1.3.14",
// -------------------------------------------------------------------------
//"org.webjars"           % "jasmine"                    % "2.1.3" % "test"

)
//import TraceurKeys._
//sourceFileNames in traceur in Assets := Seq("assets/javascripts/main.ts")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "typesafe-bintray" at "http://dl.bintray.com/typesafe/maven-releases"
//JsEngineKeys.engineType := JsEngineKeys.EngineType.Trireme
//outputFileName in traceur in Assets := "javascripts/main.ts"
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
//routesGenerator := InjectedRoutesGenerator
