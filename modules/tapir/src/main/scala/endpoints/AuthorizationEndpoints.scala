package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

sealed abstract class AuthorizationEndpoints extends BaseEndpoint {

  val authAuthorization: Endpoint[Unit, AuthorizationRequest, Unit, Unit, Any] =
    endpoint.post
      .in("api" / "auth" / "authorization")
      .in(jsonBody[AuthorizationRequest])
    // .out(jsonBody[AuthorizationResponse])

  val authAuthorizationFail: Endpoint[
    Unit,
    AuthorizationFailRequest,
    Unit,
    AuthorizationFailResponse,
    Any
  ] =
    endpoint.post
      .in("api" / "auth" / "authorization" / "fail")
      .in(jsonBody[AuthorizationFailRequest])
      .out(jsonBody[AuthorizationFailResponse])

  val authAuthorizationIssue: Endpoint[
    Unit,
    AuthorizationIssueRequest,
    Unit,
    AuthorizationIssueResponse,
    Any
  ] =
    endpoint.post
      .in("api" / "auth" / "authorization" / "issue")
      .in(jsonBody[AuthorizationIssueRequest])
      .out(jsonBody[AuthorizationIssueResponse])
}
