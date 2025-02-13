ThisBuild / scalaVersion := "3.3.3"

ThisBuild / name := "Authlete-OpenApi"
ThisBuild / version := "1.0"

ThisBuild / scalacOptions ++= Seq(
  "-no-indent",
  "-deprecation", // Warns about deprecated APIs
  "-feature", // Warns about advanced language features
  "-unchecked",
  // "-Wunused:imports",
  //   "-Wunused:privates",
  //   "-Wunused:locals",
  //   "-Wunused:explicits",
  //   "-Wunused:implicits",
  //   "-Wunused:params",
  //   "-Wvalue-discard",
  // "-language:strictEquality",
  "-Xmax-inlines:160"
)

ThisBuild / bspEnabled := true

ThisBuild / semanticdbEnabled := true

ThisBuild / cancelable := true

ThisBuild / usePipelining := true

lazy val http4sVersion = "0.23.28"
lazy val flywayVersion = "10.22.0"
lazy val postgresVersion = "42.7.4"
lazy val catsVersion = "2.11.0"
lazy val logbackVersion = "1.5.8"
lazy val circeVersion = "0.14.10"
lazy val skunkVersion = "1.1.0-M3"

val catsEffectVersion = "3.5.4"
val doobieVersion = "1.1.0-M3"
val fs2Version = "3.11.0"
val refinedVersion = "0.11.2"
val circeRefinedVersion = "0.14.9"

def circe(artifact: String): ModuleID =
  "io.circe" %% s"circe-$artifact" % circeVersion

def http4s(artifact: String): ModuleID =
  "org.http4s" %% s"http4s-$artifact" % http4sVersion

def tapir(artifact: String): ModuleID =
  "com.softwaremill.sttp.tapir" %% s"tapir-${artifact}" % "1.11.13"

def sttp(artifact: String): ModuleID=
  "com.softwaremill.sttp.client4" %% artifact % "4.0.0-M20"
//def pureconfig(artifact: String) =
//"com.github.pureconfig" %% s"pureconfig-$artifact" % pureconfigVersion

val cats = "org.typelevel" %% "cats-core" % "2.8.0"
val circeRefined = "io.circe" %% "circe-refined" % circeRefinedVersion
val refined = "eu.timepit" %% "refined" % refinedVersion
val refinedCats = "eu.timepit" %% "refined-cats" % refinedVersion
val circeGenericExtras = circe("generic-extras")
val circeCore = circe("core")
val circeGeneric = circe("generic")
val circeParser = circe("parser")
val circeLiteral = circe("literal")
val circeJawn = circe("jawn")
val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectVersion
val fs2 = "co.fs2" %% "fs2-core" % fs2Version
val http4sDsl = http4s("dsl")
val http4sServer = http4s("ember-server")
val http4sClient = http4s("ember-client")
val http4sCirce = http4s("circe")

val postgres = "org.postgresql" % "postgresql" % postgresVersion
val logback = "ch.qos.logback" % "logback-classic" % logbackVersion
val skunk = "org.tpolecat" %% "skunk-core" % skunkVersion
// val pureconfigCore = pureconfig("core")
// val pureconfigCats = pureconfig("cats")

// https://mvnrepository.com/artifact/com.authlete/authlete-java-common
//val authlete = "com.authlete" % "authlete-java-common" % "4.16"

val sttpCore = sttp("core")

val sttpJsonCommon=sttp("json-common")

val sttpUpickle=sttp("upickle")

val sttpFs2=sttp("fs2")
val sttpCats=sttp("cats")
val sttpCirce=sttp("circe")


val sttpJsoniter=sttp("jsoniter")
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
val tapirRefined = tapir("refined")

val tapirIron = tapir("iron")

val tapirJsoniter = tapir("jsoniter-scala")

val swaggerUI = tapir("swagger-ui-bundle")

lazy val core = project
  .in(file("modules/core"))
  .settings(
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
      nimbusJoseJwt,
      refined,
      refinedCats
    )
  )

lazy val api = project
  .in(file("modules/tapir"))
  .settings(
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
      sttpJsoniter
    )
  )
  .dependsOn(core)

//val scala3Version = "3.3.3"  // Scala 3.3 is an LTS series, so it is recommended to use 3.3 when publishing libraries.

lazy val root = project
  .in(file("."))
  .settings(
    organization := "dev.example", // Enter the namespace you have already acquired here
    organizationName := "local.dev", // Any name for the organization is fine as long as it is easy to understand
    startYear := Some(2024), // Year of creation
    // Specify the license. There are standard licenses available under License. If you do not have one, pass a tuple of (name, URL of that license).
    licenses += License.MIT,
    homepage := Some( // Specify the homepage of the project. In the case of GitHub, the project page is fine.
      url(
        "https://github.com/"
      )
    ),
    scmInfo := Some(
      ScmInfo( // Provide information about the source code management system. The first is the URL, and the second is the string to use when git cloning.
        url("https://github.com"),
        "https://github.com/"
      )
    ),
    developers += Developer( // Developer information. ID, name, email, homepage in order
      "id",
      "name",
      "email",
      url("http:localhost:8080")
    ),
    version := "0.0.1", // Specify the version. Semantic versioning is recommended.

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
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
      sttpCore
    )
  )
  .aggregate(core)
  .dependsOn(core)
