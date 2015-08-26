name := """testdeploy"""

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.6"


scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")


libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
   specs2 % Test,
  "com.typesafe.slick" %% "slick" % "3.0.2",
  "org.xerial" % "sqlite-jdbc" % "3.7.2",
  // -------------------------------------------------------------------------
  // Web Jars
  //"org.webjars"           % "bootstrap"                  % "3.3.2-2",
  //"org.webjars"           % "modernizr"                  % "2.8.3",
  //"org.webjars"           % "html5shiv"                  % "3.7.2",
  //"org.webjars.bower"     % "traceur"                    % "0.0.89",
  //"org.webjars.bower"     % "traceur-runtime"            % "0.0.89",
  //"org.webjars"           % "font-awesome"               % "4.3.0",
  //"org.webjars"           % "jquery"                     % "1.11.2",


  "org.webjars" % "bootstrap" % "3.3.5",
  "org.webjars" % "lodash" % "3.9.0",
  //"org.webjars"           % "momentjs"                   % "2.9.0",

  // Angular-specific.

  "org.webjars" % "angularjs" % "1.4.3-1"
  // -------------------------------------------------------------------------
  //"org.webjars"           % "jasmine"                    % "2.1.3" % "test"

)
JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

import TypescriptKeys._
import SbtWeb.autoImport._
import WebKeys._
sourceMap := true
outFile := "target/web/public/main/javascripts/wholeApp.js"
//outDir := ((webTarget in Assets).value / "typescript").absolutePath

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "typesafe-bintray" at "http://dl.bintray.com/typesafe/maven-releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
