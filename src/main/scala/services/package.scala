// import scala.concurrent.duration.DurationInt

// import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
// import common.models.JsoniterSyntaticSugar.toJson
// import sttp.client4.*
// import sttp.client4.jsoniter.*
// import sttp.model.MediaType
// import sttp.model.Uri
// import cats.effect.IO
//  import org.http4s.HttpRoutes
//  import org.http4s.dsl.io._
//  import org.http4s.headers.Authorization
//  import org.http4s.server.Router
//  import sttp.model.Header
//  import sttp.model.StatusCode
// import sttp.client4.http4s.Http4sBackend

// import org.typelevel.ci.CIStringSyntax
// package object services {

//   private val baseRequest = basicRequest
//     .auth
//     .bearer("config.apiSecret")
//     .contentType(MediaType.ApplicationJson)
//     .readTimeout(10.seconds)
//   // .readTimeout(config.requestTimeout)

//   private val baseUri = Uri("https", "config.host", 8090) // config.port)

//   private[services] def postRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
//     basicRequest
//       .post(uri.withPath("api", "authorization"))
//       .body(body.toJson)
//       .contentType(MediaType.ApplicationJson)
//       .response(asJson[R])

//   private[services] def putRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
//     basicRequest
//       .put(uri.withPath("api", "authorization"))
//       .body(body.toJson)
//       .contentType(MediaType.ApplicationJson)
//       .response(asJson[R])

//   private[services] def postRequestForm[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
//     basicRequest
//       .post(uri)
//       .body(body.toJson)
//       .response(asJson[R])
//       .contentType(MediaType.ApplicationXWwwFormUrlencoded)

//   private[services] def getRequest[R: JsonValueCodec](uri: Uri) =
//     basicRequest.get(uri).response(asJson[R])

//   private[services] def deleteRequest[R: JsonValueCodec](uri: Uri) =
//     basicRequest.get(uri).response(asJson[R])

//   // Authlete API key and secret. Needed to call Authlete's introspection API.
//   val API_KEY = "{your service API key}"
//   val API_SECRET = "{your service API secret}"

//   // Regular expression to extract the access token from the Authorization header.
//   val BEARER_TOKEN_PATTERN = "^Bearer[ ]+([^ ]+)[ ]*$".r

//   // Function to extract HTTP method and resource path from event.methodArn
//   def extractMethodAndPath(arn: String): (Option[String], Option[String]) = {
//     // The value of 'arn' is in the following format:
//     // arn:aws:execute-api:<regionID>:<accountID>:<API ID>/<stage>/<method>/<resource path>"
//     // See 'Enable Amazon API Gateway Custom Authorization' for details:
//     // http://docs.aws.amazon.com/apigateway/latest/developerguide/use-custom-authorizer.html

//     // Handle the case where event.methodArn is not set.
//     if (arn == null) {
//       // HTTP method and resource path are unknown
//       (None, None)
//     } else {
//       val arnElements = arn.split(':')
//       val resourceElements = arnElements(5).split('/')
//       val httpMethod = resourceElements(2)
//       val resourcePath = resourceElements(3)

//       // Return the HTTP method and resource path as a tuple.
//       (Some(httpMethod), Some(resourcePath))
//     }
//   }

//   // Function to extract the access token from the Authorization header.
//   // This function assumes that the input value follows "RFC 6750, 2.1. Authorization Request Header Field".
//   // For example, if the value "Bearer 123" is passed, it returns "123".
//   def extractAccessToken(authorization: String): Option[String] = {
//     // If there is no value for the Authorization header
//     if (authorization == null) {
//       // No access token.
//       None
//     } else {
//       // Check if it matches the pattern "Bearer {access token}".
//       BEARER_TOKEN_PATTERN.findFirstMatchIn(authorization) match {
//         case Some(m) => Some(m.group(1))
//         case None => None
//       }
//     }
//   }

//   // Function to return a list of required scopes as a string array from the combination of HTTP method and resource path.
//   // For example, ["profile", "email"]. If a non-empty array is returned, Authlete's introspection API on the server side
//   // will check if the access token covers all of those scopes. If this function returns null, no scope check is performed.
//   def getRequiredScopes(httpMethod: Option[String], resourcePath: Option[String]): List[String] = {
//     // Customize as needed.
//     None
//   }

//   // Function to call Authlete's introspection API
//   def introspect(accessToken: String, scopes: List[String]): IO[Response[Either[String, String]]] = {
//     val backend = Http4sBackend
//     val requestn = basicRequest
//       .post(uri"https://api.authlete.com/api/auth/introspection")
//       .auth.basic(API_KEY, API_SECRET)
//       .body(Map("token" -> accessToken, "scopes" -> scopes.getOrElse(Nil).mkString(",")))
//       .response(asString)

//     IO.fromFuture(IO(requestn.send(backend).map(response => response)))
//   }

//   // Function to generate a response to return from the Authorizer to the API Gateway
//   def generatePolicy(principalId: String, effect: String, resource: String): Map[String, Any] = {
//     Map(
//       "principalId" -> principalId,
//       "policyDocument" -> Map(
//         "Version" -> "2012-10-17",
//         "Statement" -> List(
//           Map(
//             "Action" -> "execute-api:Invoke",
//             "Effect" -> effect,
//             "Resource" -> resource
//           )
//         )
//       )
//     )
//   }

//   // Implementation of the Authorizer
//   val authorizerRoutes = HttpRoutes.of[IO] {
//     case req @ POST -> Root / "auth" =>
//       // Get the function information to be started. Extract the HTTP method and resource path from event.methodArn.
//       val methodArn = req.headers.get(ci"methodArn").map(_.head.value).getOrElse("")
//       val (httpMethod, resourcePath) = extractMethodAndPath(methodArn)

//       // Extract the access token presented by the client application from event.authorizationToken.
//       val accessToken = extractAccessToken(req.headers.get(Authorization).map(_.value).getOrElse(""))

//       // If the request from the client application does not include an access token
//       if (accessToken.isEmpty) {
//         // Output the log and instruct the API Gateway to return "401 Unauthorized".
//         IO(println(s"[$httpMethod] $resourcePath -> No access token.")) *>
//           Forbidden("Unauthorized")
//       } else {
//         // Get the list of required scopes from the combination of HTTP method and resource path.
//         val requiredScopes = getRequiredScopes(httpMethod, resourcePath)

//         // Call Authlete's introspection API to validate the access token.
//         introspect(accessToken.get, requiredScopes).flatMap {
//           case Response(Right(body), _, _, _, StatusCode.Ok) =>
//             // Log the result of the token validation.
//             IO(println(s"[$httpMethod] $resourcePath -> OK: $body")) *>
//               Ok(generatePolicy(body, "Allow", methodArn))

//           case Response(Left(error), _, _, _, status) if status == StatusCode.BadRequest || status == StatusCode.Forbidden =>
//             IO(println(s"[$httpMethod] $resourcePath -> $status: $error")) *>
//               Forbidden(generatePolicy(error, "Deny", methodArn))

//           case Response(Left(error), _, _, _, StatusCode.Unauthorized) =>
//             IO(println(s"[$httpMethod] $resourcePath -> Unauthorized: $error")) *>
//               Unauthorized("Unauthorized")

//           case Response(Left(error), _, _, _, _) =>
//             IO(println(s"[$httpMethod] $resourcePath -> Internal Server Error: $error")) *>
//               InternalServerError("Internal Server Error")
//         }
//       }
//   }

//   val httpApp = Router("/" -> authorizerRoutes).orNotFound

// }

import cats.effect.IO

import common.models.*
import common.models.JsoniterSyntaticSugar.toJson
import sttp.client4.http4s.Http4sBackend
import sttp.model.*

val scopes = List.empty[String]

import sttp.client4.jsoniter.*
import sttp.client4.Response
import sttp.client4.ResponseException

val backend = Http4sBackend.usingDefaultEmberClientBuilder[IO]()
object Authorizer {
  backend.use { backend =>
    val baseRequest
        : IO[Response[Either[ResponseException[String, Exception], IntrospectionResponse]]] = sttp
      .client4
      .basicRequest
      .auth
      .bearer("config.apiSecret")
      .contentType(MediaType.ApplicationJson)
      .post(???)
      .body(IntrospectionRequest("token", ???, Some("Subject")).toJson)
      .response(asJson[IntrospectionResponse])
      .send(backend)

    baseRequest.flatMap {
//case Right(Response(_,_,_,_,_,_,_))=> ???

      ???
    }
  }
}
