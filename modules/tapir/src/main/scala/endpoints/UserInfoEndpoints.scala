package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class UserInfoEndpoints() extends BaseEndpoint {

  /** This API gathers information about a user. /api/auth/userinfo API
    */
  val authUserinfo =
    endpoint.post
      .in("api" / "auth" / "userinfo")
      .in(jsonBody[UserinfoRequest])
      .out(jsonBody[UserinfoResponse])
      .errorOut(emptyOutput)

  /** This API generates an ID token. /api/auth/userinfo/issue API
    */
  val authUserinfoIssue =
    endpoint.post
      .in("api" / "auth" / "userinfo" / "issue")
      .in(jsonBody[UserinfoIssueRequest])
      .out(jsonBody[UserinfoIssueResponse])
      .errorOut(emptyOutput)

case class AuthUserinfoApiRequest(userinfoRequest: UserinfoRequest)
case class AuthUserinfoIssueApiRequest(
        userinfoIssueRequest: UserinfoIssueRequest
    )
}
