package endpoints

import java.util.UUID

import scala.util.matching.Regex

import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import common.models.*
import org.http4s.headers.`WWW-Authenticate`
import sttp.model.headers.*
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.{oneOfVariantValueMatcher, *}
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*
import sttp.tapir.EndpointOutput.OneOfVariant

private val INSTANCE_URI_PREFIX = "error:instance:"
//import sttp.tapir.EndpointOutput.StatusCode

final case class UserInfoEndpoints() extends BaseEndpoint {

  /**
    * This API gathers information about a user. /api/auth/userinfo API
    */
  val authUserinfo =
    endpoint
      .post
      .in("api" / "auth" / "userinfo")
      .in(jsonBody[UserInfoRequest])
      .out(jsonBody[UserInfoResponse])
      .errorOut(emptyOutput)
      .errorOut(
        oneOf(
          oneOfVariantSingletonMatcher(statusCode(StatusCode.Unauthorized))(
            UserInfoResponse.UserInfoResponseAction
          )
        )
      )

  /**
    * This API generates an ID token. /api/auth/userinfo/issue API
    */
  val authUserinfoIssue =
    endpoint
      .post
      .in("api" / "auth" / "userinfo" / "issue")
      .in(jsonBody[UserInfoIssueRequest])
      .out(jsonBody[UserInfoIssueResponse])
      .errorOut(emptyOutput)
      // .errorOutEither()
      .errorOut(
        oneOf(
          oneOfVariant[ErrorInfo](statusCode(StatusCode.Unauthorized).and(jsonBody[ErrorInfo]))
        )
      )

}
