package routes

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import domain.SessionUser
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import requests.*
import services.AuthleteService

/**
  * An implementation of a pushed authorization endpoint.
  *
  * @see
  *   <a href="https://tools.ietf.org/html/draft-lodderstedt-oauth-par" >OAuth 2.0 Pushed
  *   Authorization Requests</a>
  */
final case class PushedAuthReqRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  val routes = AuthedRoutes.of[SessionUser, F] {

    /**
      * The pushed authorization request endpoint. This uses the {@code POST} method and the same
      * client authentication as is available on the Token Endpoint.
      */
    case req @ POST -> Root / "api" / "par" as user =>
      req
        .req
        .as[UrlForm]
        .flatMap { form =>
          val parameters = form.values

          ???

        }

  }

}
