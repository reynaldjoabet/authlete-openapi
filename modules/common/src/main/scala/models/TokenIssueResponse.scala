package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   The code which represents the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  * @param accessToken
  *   The newly issued access token.
  * @param accessTokenExpiresAt
  *   The datetime at which the newly issued access token will expire. The value is represented in
  *   milliseconds since the Unix epoch (1970-01-01).
  * @param accessTokenDuration
  *   The duration of the newly issued access token in seconds.
  * @param refreshToken
  *   The newly issued refresh token.
  * @param refreshTokenExpiresAt
  *   The datetime at which the newly issued refresh token will expire. The value is represented in
  *   milliseconds since the Unix epoch (1970-01-01).
  * @param refreshTokenDuration
  *   The duration of the newly issued refresh token in seconds.
  * @param clientId
  *   The client ID.
  * @param clientIdAlias
  *   The client ID alias when the token request was made. If the client did not have an alias, this
  *   parameter is `null`. Also, if the token request was invalid and it failed to identify a
  *   client, this parameter is `null`.
  * @param clientIdAliasUsed
  *   The flag which indicates whether the client ID alias was used when the token request was made.
  *   `true` if the client ID alias was used when the token request was made.
  * @param subject
  *   The subject (= resource owner\'s ID) of the access token. Even if an access token has been
  *   issued by the call of `/api/auth/token` API, this parameter is `null` if the flow of the token
  *   request was [Client Credentials
  *   Flow](https://datatracker.ietf.org/doc/html/rfc6749#section-4.4)
  *   (`grant_type=client_credentials`) because it means the access token is not associated with any
  *   specific end-user.
  * @param scopes
  *   The scopes covered by the access token.
  * @param properties
  *   The extra properties associated with the access token. This parameter is `null` when no extra
  *   property is associated with the issued access token.
  * @param jwtAccessToken
  *   The newly issued access token in JWT format. If the authorization server is configured to
  *   issue JWT-based access tokens (= if the service\'s `accessTokenSignAlg` value is a non-null
  *   value), a JWT-based access token is issued along with the original random-string one.
  * @param accessTokenResources
  *   The target resources of the access token being issued. See \"Resource Indicators for OAuth
  *   2.0\" for details.
  * @param authorizationDetails
  *
  * @param serviceAttributes
  *   The attributes of this service that the client application belongs to.
  * @param clientAttributes
  *   The attributes of the client.
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class TokenIssueResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[TokenIssueResponse.TokenIssueResponseAction],
    responseContent: Option[String],
    accessToken: Option[String],
    accessTokenExpiresAt: Option[Long],
    accessTokenDuration: Option[Long],
    refreshToken: Option[String],
    refreshTokenExpiresAt: Option[Long],
    refreshTokenDuration: Option[Long],
    clientId: Option[Long],
    clientIdAlias: Option[String],
    clientIdAliasUsed: Option[Boolean],
    subject: Option[String],
    scopes: List[String] = List.empty,
    properties: List[Property] = List.empty,
    jwtAccessToken: Option[String],
    accessTokenResources: List[String] = List.empty,
    authorizationDetails: Option[AuthzDetails],
    serviceAttributes: List[Pair] = List.empty,
    clientAttributes: List[Pair] = List.empty,
    clientEntityId: Option[String],
    clientEntityIdUsed: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenIssueResponse {

  enum TokenIssueResponseAction derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case OK

  }
  // implicit val codec: JsonValueCodec[TokenIssueResponse] =
  // sonCodecMaker.make

  enum TokenIssueResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    // case OK
  }

}
