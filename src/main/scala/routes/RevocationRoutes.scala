package routes

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import domain.SessionUser
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import services.AuthleteService

final case class RevocationRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  /**
    * Revocation endpoint which supports RFC 7009.
    *
    * @see
    *   <a href="http://tools.ietf.org/html/rfc7009" >RFC 7009, OAuth 2.0 Token Revocation</a>
    */

  val routes = AuthedRoutes.of[SessionUser, F] { case POST -> Root / "revocation" as session =>
    val body: RevocationRequest = RevocationRequest("parameters")
    authleteService
      .revocation(body)
      .flatMap { resp =>
        resp
          .action
          .fold(Status.InternalServerError()) {
            case RevocationResponse.RevocationResponseAction.INVALID_CLIENT => ??? // Status.Unauthorized
            case RevocationResponse.RevocationResponseAction.INTERNAL_SERVER_ERROR =>
              Status.InternalServerError()
            case RevocationResponse.RevocationResponseAction.BAD_REQUEST => Status.BadRequest()
            case RevocationResponse.RevocationResponseAction.OK          => Status.Ok()
          }
      }
  }

  import com.authlete.common.dto.RevocationResponse

}
