package routes

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import domain.SessionUser
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.*
import services.AuthleteService

/**
  * An implementation of userinfo endpoint (<a href=
  * "https://openid.net/specs/openid-connect-core-1_0.html#UserInfo" >OpenID Connect Core 1&#x2E;0,
  * 5&#x2E;3&#x2E; UserInfo Endpoint</a>).
  *
  * @see
  *   <a href="https://openid.net/specs/openid-connect-core-1_0.html#UserInfo" >OpenID Connect Core
  *   10, 5.3. UserInfo Endpoint</a>
  */

final case class UserInfoRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  val routes = AuthedRoutes.of[SessionUser, F] {
    case GET -> Root / "userinfo" as session =>
      val missingAccessTokenChallenge = "Bearer error=\"invalid_token\",error_description=\""
        + "An access token must be sent as a Bearer Token. "
        + "See OpenID Connect Core 1.0, 5.3.1. UserInfo Request for details.\""
      lazy val userInfoReques: UserInfoRequest = UserInfoRequest("")

      authleteService
        .userinfo(userInfoReques)
        .flatMap { resp =>
          resp
            .action
            .fold(Status.InternalServerError()) {
              case UserInfoResponse.UserInfoResponseAction.INTERNAL_SERVER_ERROR =>
                Status.InternalServerError()
              case UserInfoResponse.UserInfoResponseAction.BAD_REQUEST  => Status.BadRequest()
              case UserInfoResponse.UserInfoResponseAction.UNAUTHORIZED => ??? // Status.Unauthorized()
              case UserInfoResponse.UserInfoResponseAction.FORBIDDEN    => Status.Forbidden()
              case UserInfoResponse.UserInfoResponseAction.OK =>
                val userInfoIssueRequest: UserInfoIssueRequest = UserInfoIssueRequest(
                  resp.token.getOrElse(""),
                  Option(resp.claims.mkString(",")),
                  resp.subject
                )
                processUserInfoIssueResponse(userInfoIssueRequest)
            }
        }
    case req @ POST -> Root / "userinfo" as session =>
      ???

  }

  private def processUserInfoIssueResponse(body: UserInfoIssueRequest): F[Response[F]] =
    authleteService
      .userinfoIssue(body)
      .flatMap { resp =>
        val content = resp.responseContent
        resp
          .action
          .fold(Status.InternalServerError()) {
            case UserInfoIssueResponse.UserInfoIssueResponseAction.INTERNAL_SERVER_ERROR =>
              Status.InternalServerError()
            case UserInfoIssueResponse.UserInfoIssueResponseAction.BAD_REQUEST =>
              Status.BadRequest()
            case UserInfoIssueResponse.UserInfoIssueResponseAction.UNAUTHORIZED =>
              Status.Unauthorized(
                `WWW-Authenticate`(Challenge("Bearer", "realm", Map("charset" -> "UTF-8")))
              ) // WWW-Authenticate: Bearer realm="DigitalOcean", error="invalid_token", error_description="The access token is invalid"
            case UserInfoIssueResponse.UserInfoIssueResponseAction.FORBIDDEN => Status.Forbidden()
            case UserInfoIssueResponse.UserInfoIssueResponseAction.JSON =>
              Ok(content.get, `Content-Type`(MediaType.application.json, Charset.`UTF-8`))
            case UserInfoIssueResponse.UserInfoIssueResponseAction.JWT =>
              Ok(content.get, `Content-Type`(MediaType.application.jwt, Charset.`UTF-8`))
          }
      }

}
