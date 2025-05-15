ThisBuild / scalaVersion := "3.3.3"

ThisBuild / name    := "Authlete-OpenApi"
ThisBuild / version := "1.0"

ThisBuild / scalacOptions ++= Seq(
  "-no-indent",
  "-deprecation", // Warns about deprecated APIs
  "-feature",     // Warns about advanced language features
  "-unchecked",
  // "-Wunused:imports",
  //   "-Wunused:privates",
  //   "-Wunused:locals",
  //   "-Wunused:explicits",
  //   "-Wunused:implicits",
  //   "-Wunused:params",
  //   "-Wvalue-discard",
  // "-language:strictEquality",
  "-Xmax-inlines:100000"
)

ThisBuild / bspEnabled := true

ThisBuild / semanticdbEnabled := true

ThisBuild / cancelable := true

//ThisBuild / usePipelining := true //causes issues with multiple modules

lazy val http4sVersion   = "0.23.28"
lazy val flywayVersion   = "10.22.0"
lazy val postgresVersion = "42.7.4"
lazy val catsVersion     = "2.11.0"
lazy val logbackVersion  = "1.5.8"
lazy val circeVersion    = "0.14.10"
lazy val skunkVersion    = "1.1.0-M3"

val catsEffectVersion   = "3.5.4"
val doobieVersion       = "1.1.0-M3"
val fs2Version          = "3.11.0"
val refinedVersion      = "0.11.2"
val circeRefinedVersion = "0.14.9"

def circe(artifact: String): ModuleID =
  "io.circe" %% s"circe-$artifact" % circeVersion

def http4s(artifact: String): ModuleID =
  "org.http4s" %% s"http4s-$artifact" % http4sVersion

def tapir(artifact: String): ModuleID =
  "com.softwaremill.sttp.tapir" %% s"tapir-$artifact" % "1.11.13"

def sttp(artifact: String): ModuleID =
  "com.softwaremill.sttp.client4" %% artifact % "4.0.0-M20"
//def pureconfig(artifact: String) =
//"com.github.pureconfig" %% s"pureconfig-$artifact" % pureconfigVersion

val cats               = "org.typelevel" %% "cats-core"     % "2.8.0"
val circeRefined       = "io.circe"      %% "circe-refined" % circeRefinedVersion
val refined            = "eu.timepit"    %% "refined"       % refinedVersion
val refinedCats        = "eu.timepit"    %% "refined-cats"  % refinedVersion
val circeGenericExtras = circe("generic-extras")
val circeCore          = circe("core")
val circeGeneric       = circe("generic")
val circeParser        = circe("parser")
val circeLiteral       = circe("literal")
val circeJawn          = circe("jawn")
val catsEffect         = "org.typelevel" %% "cats-effect"   % catsEffectVersion
val fs2                = "co.fs2"        %% "fs2-core"      % fs2Version
val http4sDsl          = http4s("dsl")
val http4sServer       = http4s("ember-server")
val http4sClient       = http4s("ember-client")
val http4sCirce        = http4s("circe")

val postgres = "org.postgresql" % "postgresql"      % postgresVersion
val logback  = "ch.qos.logback" % "logback-classic" % logbackVersion
val skunk    = "org.tpolecat"  %% "skunk-core"      % skunkVersion
// val pureconfigCore = pureconfig("core")
// val pureconfigCats = pureconfig("cats")

// https://mvnrepository.com/artifact/com.authlete/authlete-java-common
val authlete = "com.authlete" % "authlete-java-common" % "4.16"

val sttpCore = sttp("core")

val sttpJsonCommon = sttp("json-common")

val sttpUpickle = sttp("upickle")

val sttpFs2   = sttp("fs2")
val sttpCats  = sttp("cats")
val sttpCirce = sttp("circe")

val sttpJsoniter = sttp("jsoniter")

// https://mvnrepository.com/artifact/com.softwaremill.sttp.client4/async-http-client-backend-fs2
val clientBackendFs2 = sttp("async-http-client-backend-fs2")

// https://mvnrepository.com/artifact/com.softwaremill.sttp.client4/http4s-backend
val http4sBackend = sttp("http4s-backend")
// https://mvnrepository.com/artifact/com.nimbusds/nimbus-jose-jwt
val nimbusJoseJwt = "com.nimbusds" % "nimbus-jose-jwt" % "9.47"

// https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on
//Bouncy Castle Provider
//libraryDependencies += "org.bouncycastle" % "bcprov-jdk18on" % "1.79"

val tapirCore = tapir("core")

val tapirCirce = tapir("json-circe")

val openapiDocs = tapir(
  "openapi-docs"
) //If you only need to generate an OpenAPI specification (without serving a UI)

val tapirHttp4sServer = tapir("http4s-server")
val tapirRefined      = tapir("refined")

val tapirIron = tapir("iron")

val tapirJsoniter = tapir("jsoniter-scala")

val swaggerUI = tapir("swagger-ui-bundle")

// https://mvnrepository.com/artifact/com.github.plokhotnyuk.jsoniter-scala/jsoniter-scala-core
val jsoniter =
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.33.2"

// https://mvnrepository.com/artifact/com.github.plokhotnyuk.jsoniter-scala/jsoniter-scala-macros
val jsoniterMacros =
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.33.2" % "provided"

lazy val common = project
  .in(file("modules/common"))
  .settings(
    name := "common",
    libraryDependencies ++= Seq(
      cats,
      catsEffect,
      circeCore,
      circeGeneric,
      // circeGenericExtras,
      circeLiteral,
      circeParser,
      circeJawn,
      circeRefined,
      circeRefined,
      refined,
      refinedCats,
      tapirJsoniter,
      tapirCore,
      jsoniter,
      jsoniterMacros,
      authlete
    )
  )

lazy val api = project
  .in(file("modules/api"))
  .settings(
    name := "api",
    libraryDependencies ++= Seq(
      tapirCore,
      tapirCirce,
      openapiDocs,
      tapirHttp4sServer,
      tapirRefined,
      tapirJsoniter,
      tapirIron,
      sttpCore,
      sttpUpickle,
      sttpFs2,
      sttpCirce,
      sttpCats,
      sttpJsoniter,
      jsoniterMacros,
      nimbusJoseJwt,
      http4sClient,
      clientBackendFs2,
      http4sBackend
    )
  )
  .dependsOn(common % "compile->compile;test->test")

//val scala3Version = "3.3.3"  // Scala 3.3 is an LTS series, so it is recommended to use 3.3 when publishing libraries.

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      fs2,
      http4sDsl,
      http4sServer,
      http4sClient,
      http4sCirce,
      postgres,
      logback,
      skunk,
      sttpCore,
      jsoniter,
      jsoniterMacros,
      nimbusJoseJwt,
      clientBackendFs2,
      http4sBackend,
      sttpJsoniter,
      authlete,
      circeCore,
      circeGeneric,
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )
  // .aggregate(common)
  .dependsOn(common  % "compile->compile;test->test")

//javacOptions ++= Seq("-source", "17", "-target", "17")
