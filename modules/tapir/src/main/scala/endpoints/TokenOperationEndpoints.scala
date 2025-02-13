package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class TokenOperationEndpoints() extends BaseEndpoint {

  /** Create an access token. /api/auth/token/create API
    */
  val authTokenCreate =
    endpoint.post
      .in("api" / "auth" / "token" / "create")
      .in(jsonBody[TokenCreateRequest])
      .out(jsonBody[TokenCreateResponse])
      .errorOut(emptyOutput)

  /** Delete an access token. /api/auth/token/delete API
    */
  val authTokenDelete =
    endpoint.delete
      .in(
        "api" / "auth" / "token" / "delete" / path[String](
          "accessTokenIdentifier"
        )
      )
      // .in(jsonBody[TokenDeleteRequest])
      .out(emptyOutput)
      .errorOut(emptyOutput)

  /** Get a list of client applications that an end-user has authorized.
    * /api/auth/token/get/list API
    */
  val authTokenGetList =
    endpoint.get
      .in("api" / "auth" / "token" / "get" / "list")
      .in(query[Option[String]]("clientIdentifier"))
      .in(query[Option[String]]("subject"))
      .in(query[Option[Int]]("start"))
      .in(query[Option[Int]]("end"))
      // .in(jsonBody[TokenGetListRequest])
      .out(jsonBody[TokenGetListResponse])
      .errorOut(emptyOutput)

  /** Revoke an access token. /api/auth/token/revoke API
    */
  val authTokenRevoke =
    endpoint.post
      .in("api" / "auth" / "token" / "revoke")
      .in(jsonBody[TokenRevokeRequest])
      .out(jsonBody[TokenRevokeResponse])
      .errorOut(emptyOutput)

  /** Update an access token. /api/auth/token/update API
    */
  val authTokenUpdate =
    endpoint.post
      .in("api" / "auth" / "token" / "update")
      .in(jsonBody[TokenUpdateRequest])
      .out(jsonBody[TokenUpdateResponse])
      .errorOut(emptyOutput)

case class AuthTokenCreateApiRequest(tokenCreateRequest: TokenCreateRequest)
case class AuthTokenDeleteApiRequest(accessTokenIdentifier: String)
case class AuthTokenGetListApiRequest(
        clientIdentifier: Option[String],
        subject: Option[String],
        start: Option[Int],
        end: Option[Int]
    )
case class AuthTokenRevokeApiRequest(tokenRevokeRequest: TokenRevokeRequest)
case class AuthTokenUpdateApiRequest(tokenUpdateRequest: TokenUpdateRequest)
}
