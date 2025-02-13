package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class PushedAuthorizationEndpoints() extends BaseEndpoint {

  /** This API creates a pushed request authorization. It authenticates the
    * client and creates an authorization_uri to be returned by the
    * authorization server. /api/pushed_auth_req API
    */
  val pushedAuthReq =
    endpoint.post
      .in("api" / "pushed_auth_req")
      // .in(jsonBody[PushedAuthReqApiRequest])
      .out(jsonBody[PushedAuthorizationResponse])
      .errorOut(emptyOutput)
case class PushedAuthReqApiRequest(
        pushedAuthorizationRequest: PushedAuthorizationRequest
    )
}
