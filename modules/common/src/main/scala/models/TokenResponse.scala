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
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  * @param username
  *   The value of `username` request parameter in the token request. The client application must
  *   specify username when it uses [Resource Owner Password
  *   Grant](https://datatracker.ietf.org/doc/html/rfc6749#section-4.3). In other words, when the
  *   value of `grant_type` request parameter is `password`, `username` request parameter must come
  *   along. This parameter has a value only if the value of `grant_type` request parameter is
  *   `password` and the token request is valid.
  * @param password
  *   The value of `password` request parameter in the token request. The client application must
  *   specify password when it uses [Resource Owner Password
  *   Grant](https://datatracker.ietf.org/doc/html/rfc6749#section-4.3). In other words, when the
  *   value of `grant_type` request parameter is `password`, `password` request parameter must come
  *   along. This parameter has a value only if the value of `grant_type` request parameter is
  *   `password` and the token request is valid.
  * @param ticket
  *   The ticket which is necessary to call Authlete\'s `/auth/token/fail` API or
  *   `/auth/token/issue` API. This parameter has a value only if the value of `grant_type` request
  *   parameter is `password` and the token request is valid.
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
  * @param idToken
  *   The newly issued ID token. Note that an ID token is issued from a token endpoint only when the
  *   `response_type` request parameter of the authorization request to an authorization endpoint
  *   has contained `code` and the `scope` request parameter has contained `openid`.
  * @param grantType
  *   The grant type of the token request.
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
  * @param resources
  *   The resources specified by the `resource` request parameters in the token request. See
  *   \"Resource Indicators for OAuth 2.0\" for details.
  * @param accessTokenResources
  *   The target resources of the access token being issued. See \"Resource Indicators for OAuth
  *   2.0\" for details.
  * @param authorizationDetails
  *
  * @param serviceAttributes
  *   The attributes of this service that the client application belongs to.
  * @param clientAttributes
  *   The attributes of the client.
  * @param grantId
  *   the value of the `grant_id` request parameter of the device authorization request. The
  *   `grant_id` request parameter is defined in [Grant Management for OAuth
  *   2.0](https://openid.net/specs/fapi-grant-management.html) , which is supported by Authlete 2.3
  *   and newer versions.
  * @param audiences
  *   The audiences on the token exchange request
  * @param requestedTokenType
  *
  * @param subjectToken
  *
  * @param subjectTokenType
  *
  * @param subjectTokenInfo
  *
  * @param actorToken
  *
  * @param actorTokenType
  *
  * @param actorTokenInfo
  *
  * @param assertion
  *   For RFC 7523 JSON Web Token (JWT) Profile for OAuth 2.0 Client Authentication and
  *   Authorization Grants
  * @param previousRefreshTokenUsed
  *   Indicate whether the previous refresh token that had been kept in the database for a short
  *   time was used
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class TokenResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[TokenResponse.TokenResponseAction],
    responseContent: Option[String],
    username: Option[String],
    password: Option[String],
    ticket: Option[String],
    accessToken: Option[String],
    accessTokenExpiresAt: Option[Long],
    accessTokenDuration: Option[Long],
    refreshToken: Option[String],
    refreshTokenExpiresAt: Option[Long],
    refreshTokenDuration: Option[Long],
    idToken: Option[String],
    grantType: Option[String],
    clientId: Option[Long],
    clientIdAlias: Option[String],
    clientIdAliasUsed: Option[Boolean],
    subject: Option[String],
    scopes: List[String] = List.empty,
    properties: List[Property] = List.empty,
    jwtAccessToken: Option[String],
    resources: List[String] = List.empty,
    accessTokenResources: List[String] = List.empty,
    authorizationDetails: Option[AuthzDetails],
    serviceAttributes: List[Pair] = List.empty,
    clientAttributes: List[Pair] = List.empty,
    grantId: Option[String],
    audiences: List[String] = List.empty,
    requestedTokenType: Option[TokenType],
    subjectToken: Option[String],
    subjectTokenType: Option[TokenType],
    subjectTokenInfo: Option[TokenInfo],
    actorToken: Option[String],
    actorTokenType: Option[TokenType],
    actorTokenInfo: Option[TokenInfo],
    assertion: Option[String],
    previousRefreshTokenUsed: Option[Boolean],
    clientEntityId: Option[String],
    clientEntityIdUsed: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenResponse {

  enum TokenResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case INVALID_CLIENT
    case BAD_REQUEST
    case PASSWORD
    case OK
    case TOKEN_EXCHANGE
    case JWT_BEARER

  }

  enum TokenResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case INVALID_CLIENT
    case BAD_REQUEST
    case PASSWORD
    case OK
    case TOKEN_EXCHANGE
    case JWT_BEARER

  }

  // implicit val codec: JsonValueCodec[TokenResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

}
