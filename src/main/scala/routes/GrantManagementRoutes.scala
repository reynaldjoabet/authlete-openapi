package routes

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`WWW-Authenticate`
import org.http4s.headers.Authorization
import org.typelevel.ci.*
import services.AuthleteService
//import services.DpopService.*

/**
  * An implementation of Grant Management Endpoint.
  *
  * @see
  *   <a href="https://openid.net/specs/fapi-grant-management.html" >Grant Management for OAuth
  *   2.0</a>
  */

final case class GrantManagementRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  val routes = HttpRoutes.of[F] {

    /**
      * The entry point for grant management 'query' requests.
      */
    case req @ GET -> Root / "gm" / grantId =>
      // .setGmAction(if req.method.name == "DELETE" then GMAction.REVOKE else GMAction.QUERY)
      // val gmAction= req.method.name match {
      //   case "DELETE" => GrantManagementAction.REVOKE
      //   case _ => GrantManagementAction.QUERY
      // }
      val gmRequest = GMRequest(
        extractAccessToken(req),
        scopes = List("scope1", "scope2"),
        subject = Some("subject"),
        clientCertificate = extractClientCertificate(req),
        dpop = extractDpop(req),
        None,
        None,
        List("resource1", "resource2"),
        Some(GrantManagementAction.QUERY),
        Some(grantId)
      )
      processGMResponse(gmRequest)

    /**
      * The entry point for grant management 'revoke' requests.
      */
    case req @ DELETE -> Root / "gm" / grantId =>

      val gmRequest = GMRequest(
        extractAccessToken(req),
        scopes = List("scope1", "scope2"),
        subject = Some("subject"),
        clientCertificate = extractClientCertificate(req),
        dpop = extractDpop(req),
        None,
        None,
        List("resource1", "resource2"),
        Some(GrantManagementAction.REVOKE),
        Some(grantId)
      )
      processGMResponse(gmRequest)

  }

  private def processGMResponse(body: GMRequest): F[Response[F]] =
    authleteService
      .gm(body)
      .flatMap {
        resp =>
          val content                  = resp.responseContent.getOrElse("")
          val dpopNonceHeader          = resp.dpopNonce.map(Header.Raw(ci"DPoP-Nonce", _))
          val responseHeaders: Headers = dpopNonceHeader.map(Headers(_)).getOrElse(Headers.empty)
        resp
          .action
          .fold(Status.InternalServerError()) {
            case GMResponse.GMResponseAction.OK => Status.Ok(content, responseHeaders)
            case GMResponse.GMResponseAction.NO_CONTENT =>
              Status
                .NoContent()
                .map { resp =>
                  // dpopNonceHeader.map(resp.addHeader(_)).getOrElse(resp)
                  resp
                }
            case GMResponse.GMResponseAction.UNAUTHORIZED =>
              Status.Unauthorized(
                `WWW-Authenticate`(Challenge("Bearer", "realm", Map("charset" -> "UTF-8"))),
                headers = responseHeaders
              )
            case GMResponse.GMResponseAction.FORBIDDEN => Status.Forbidden(content, responseHeaders)
            case GMResponse.GMResponseAction.NOT_FOUND => Status.NotFound(content, responseHeaders)
            case GMResponse.GMResponseAction.CALLER_ERROR |
                GMResponse.GMResponseAction.AUTHLETE_ERROR =>
              Status.InternalServerError(content, responseHeaders)
            // case GMResponse.Action.AUTHLETE_ERROR => Status.InternalServerError(content,responseHeaders)
          }
      }

  private def extractAccessToken(req: Request[F]) =
    req
      .headers
      .get[Authorization]
      .flatMap(
        _.credentials match {
          case Credentials.Token(AuthScheme.Bearer, token) => Option(token)
          case _                                           => None
        }
      )

  private def extractDpop(req: Request[F]) =
    req.headers.get(ci"Authorization").map(_.head.value).flatMap(extractDpopToken(_))

  private def extractDpopToken(token: String)           = ???
  private def extractClientCertificate(req: Request[F]) = ???

}
