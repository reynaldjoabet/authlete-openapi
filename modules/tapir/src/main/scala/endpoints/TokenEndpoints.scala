package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class TokenEndpoints() extends BaseEndpoint {

  /** This API parses request parameters of an authorization request and returns
    * necessary data for the authorization server implementation to process the
    * authorization request further. /api/auth/token API
    */
  val authToken =
    endpoint.post
      .in("api" / "auth" / "token")
      .in(header("Content-Type", "application/json"))
      //.in(auth.basic[Option[String]]().map(identity(_))(identity(_)))
      // .in(jsonBody[AuthTokenApiRequest])
      .out(jsonBody[TokenResponse])
      .errorOut(emptyOutput)

  /** This API generates a content of an error token response that the
    * authorization server implementation returns to the client application.
    * /api/auth/token/fail API
    */
  val authTokenFail =
    endpoint.post
      .in("api" / "auth" / "token" / "fail")
      // .in(jsonBody[AuthTokenFailApiRequest])
      .out(jsonBody[TokenFailResponse])
      .errorOut(emptyOutput)

  /** This API generates a content of a successful token response that the
    * authorization server implementation returns to the client application.
    * /api/auth/token/issue API
    */
  val authTokenIssue =
    endpoint.post
      .in("api" / "auth" / "token" / "issue")
      // .in(jsonBody[AuthTokenIssueApiRequest])
      .out(jsonBody[TokenIssueResponse])
      .errorOut(emptyOutput)

case class AuthTokenApiRequest(tokenRequest: TokenRequest)
case class AuthTokenFailApiRequest(tokenFailRequest: TokenFailRequest)
case class AuthTokenIssueApiRequest(tokenIssueRequest: TokenIssueRequest)
}
