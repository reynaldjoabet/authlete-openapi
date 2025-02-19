package endpoints

import common.models.*
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

sealed abstract class AuthorizationEndpoints extends BaseEndpoint {

  val authAuthorization: Endpoint[Unit, AuthorizationRequest, Unit, AuthorizationResponse, Any] =
    endpoint
      .post
      .in("api" / "auth" / "authorization")
      .in(jsonBody[AuthorizationRequest])
      .out(jsonBody[AuthorizationResponse])

  val authAuthorizationFail: Endpoint[
    Unit,
    AuthorizationFailRequest,
    Unit,
    AuthorizationFailResponse,
    Any
  ] =
    endpoint
      .post
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
    endpoint
      .post
      .name("Fetch newest Posts")
      .summary("Paginate newest Posts for a Channel")
      .description("Returns paginated Posts for a specific Channel")
      .in("api" / "auth" / "authorization" / "issue")
      .in(jsonBody[AuthorizationIssueRequest])
      .out(jsonBody[AuthorizationIssueResponse])

//  private val errorMapping = oneOf[AuthorizationIssueResponse.AuthorizationIssueResponseAction](
//       oneOfVariant[AuthorizationIssueResponse.AuthorizationIssueResponseAction.Location](statusCode(StatusCode.Ok).and(jsonBody[String])),
//       oneOfVariant(statusCode(StatusCode.BadRequest).and(jsonBody[ErrorResponse]))
//     )

}
