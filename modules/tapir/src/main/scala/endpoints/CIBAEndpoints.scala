package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

abstract class CIBAEndpoints extends BaseEndpoint {

  /** This API parses request parameters of a [backchannel authentication
    * request](https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html#auth_request)
    * and returns necessary data for the authorization server implementation to
    * process the backchannel authentication request further.
    * /api/backchannel/authentication API
    */
  val backchannelAuthentication: Endpoint[
    Unit,
    BackchannelAuthenticationRequest,
    Unit,
    BackchannelAuthenticationResponse,
    Any
  ] =
    endpoint.post
      .in("api" / "backchannel" / "authentication")
      .in(jsonBody[BackchannelAuthenticationRequest])
      .out(jsonBody[BackchannelAuthenticationResponse])

    /** This API returns information about what action the authorization server
      * should take after it receives the result of end-user's decision about
      * whether the end-user has approved or rejected a client application's
      * request on the authentication device.
      * /api/backchannel/authentication/complete API
      */
  val backchannelAuthenticationComplete: Endpoint[
    Unit,
    BackchannelAuthenticationCompleteRequest,
    Unit,
    BackchannelAuthenticationCompleteResponse,
    Any
  ] =
    endpoint.post
      .in("api" / "backchannel" / "authentication" / "complete")
      .in(jsonBody[BackchannelAuthenticationCompleteRequest])
      .out(jsonBody[BackchannelAuthenticationCompleteResponse])

    /** The API prepares JSON that contains an error. The JSON should be used as
      * the response body of the response which is returned to the client from
      * the [backchannel authentication
      * endpoint](https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html#auth_backchannel_endpoint).
      * /api/backchannel/authentication/fail API
      */
  val backchannelAuthenticationFail: Endpoint[
    Unit,
    BackchannelAuthenticationFailRequest,
    Unit,
    BackchannelAuthenticationFailResponse,
    Any
  ] =
    endpoint.post
      .in("api" / "backchannel" / "authentication" / "fail")
      .in(jsonBody[BackchannelAuthenticationFailRequest])
      .out(jsonBody[BackchannelAuthenticationFailResponse])

    /** This API prepares JSON that contains an `auth_req_id`. The JSON should
      * be used as the response body of the response which is returned to the
      * client from the [backchannel authentication
      * endpoint](https://openid.net/specs/openid-client-initiated-backchannel-authentication-core-1_0.html#auth_backchannel_endpoint)
      * /api/backchannel/authentication/issue API
      */
  val backchannelAuthenticationIssue: Endpoint[
    Unit,
    BackchannelAuthenticationIssueRequest,
    Unit,
    BackchannelAuthenticationIssueResponse,
    Any
  ] =
    endpoint.post
      .in("api" / "backchannel" / "authentication" / "issue")
      .in(jsonBody[BackchannelAuthenticationIssueRequest])
      .out(jsonBody[BackchannelAuthenticationIssueResponse])
}
