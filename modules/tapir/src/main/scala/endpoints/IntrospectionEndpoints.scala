package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class IntrospectionEndpoints() extends BaseEndpoint {

  /** This API gathers information about an access token.
    * /api/auth/introspection API
    */
  val authIntrospection =
    endpoint.post
      .in("api" / "auth" / "introspection")
      // .in(jsonBody[AuthIntrospectionApiRequest])
      .out(jsonBody[IntrospectionResponse])
      .errorOut(emptyOutput)

  /** This API exists to help your authorization server provide its own
    * introspection API which complies with RFC 7662 (OAuth 2.0 Token
    * Introspection). /api/auth/introspection/standard API
    */
  val authIntrospectionStandard =
    endpoint.post
      .in("api" / "auth" / "introspection" / "standard")
      // .in(jsonBody[AuthIntrospectionStandardApiRequest])
      .out(jsonBody[StandardIntrospectionResponse])
      .errorOut(emptyOutput)

case class AuthIntrospectionApiRequest(
        introspectionRequest: IntrospectionRequest
    )
case class AuthIntrospectionStandardApiRequest(
        standardIntrospectionRequest: StandardIntrospectionRequest
    )
}
