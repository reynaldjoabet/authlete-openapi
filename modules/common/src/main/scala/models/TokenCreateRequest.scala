package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param grantType
  *
  * @param clientId
  *   The ID of the client application which will be associated with a newly created access token.
  * @param subject
  *   The subject (= unique identifier) of the user who will be associated with a newly created
  *   access token. This parameter is required unless the grant type is `CLIENT_CREDENTIALS`. The
  *   value must consist of only ASCII characters and its length must not exceed 100.
  * @param scopes
  *   The scopes which will be associated with a newly created access token. Scopes that are not
  *   supported by the service cannot be specified and requesting them will cause an error.
  * @param accessTokenDuration
  *   The duration of a newly created access token in seconds. If the value is 0, the duration is
  *   determined according to the settings of the service.
  * @param refreshTokenDuration
  *   The duration of a newly created refresh token in seconds. If the value is 0, the duration is
  *   determined according to the settings of the service. A refresh token is not created (1) if the
  *   service does not support `REFRESH_TOKEN`, or (2) if the specified grant type is either
  *   `IMPLICIT`or `CLIENT_CREDENTIALS`.
  * @param properties
  *   Extra properties to associate with a newly created access token. Note that properties
  *   parameter is accepted only when the HTTP method of the request is POST and Content-Type of the
  *   request is `application/json`, so don\'t use `GET` method or
  *   `application/x-www-form-urlencoded` if you want to specify properties.
  * @param clientIdAliasUsed
  *   A boolean request parameter which indicates whether to emulate that the client ID alias is
  *   used instead of the original numeric client ID when a new access token is created. This has an
  *   effect only on the value of the aud claim in a response from [UserInfo
  *   endpoint](https://openid.net/specs/openid-connect-core-1_0.html#UserInfo). When you access the
  *   UserInfo endpoint (which is expected to be implemented using Authlete\'s `/api/auth/userinfo`
  *   API and `/api/auth/userinfo/issue` API) with an access token which has been created using
  *   Authlete\'s `/api/auth/token/create` API with this property (`clientIdAliasUsed`) `true`, the
  *   client ID alias is used as the value of the aud claim in a response from the UserInfo
  *   endpoint. Note that if a client ID alias is not assigned to the client when Authlete\'s
  *   `/api/auth/token/create` API is called, this property (`clientIdAliasUsed`) has no effect (it
  *   is always regarded as `false`).
  * @param accessToken
  *   The value of the new access token. The `/api/auth/token/create` API generates an access token.
  *   Therefore, callers of the API do not have to specify values of newly created access tokens.
  *   However, in some cases, for example, if you want to migrate existing access tokens from an old
  *   system to Authlete, you may want to specify values of access tokens. In such a case, you can
  *   specify the value of a newly created access token by passing a non-null value as the value of
  *   accessToken request parameter. The implementation of the `/api/auth/token/create` uses the
  *   value of the accessToken request parameter instead of generating a new value when the request
  *   parameter holds a non-null value. Note that if the hash value of the specified access token
  *   already exists in Authlete\'s database, the access token cannot be inserted and the
  *   `/api/auth/token/create` API will report an error.
  * @param refreshToken
  *   The value of the new refresh token. The `/api/auth/token/create` API may generate a refresh
  *   token. Therefore, callers of the API do not have to specify values of newly created refresh
  *   tokens. However, in some cases, for example, if you want to migrate existing refresh tokens
  *   from an old system to Authlete, you may want to specify values of refresh tokens. In such a
  *   case, you can specify the value of a newly created refresh token by passing a non-null value
  *   as the value of refreshToken request parameter. The implementation of the
  *   `/api/auth/token/create` uses the value of the refreshToken request parameter instead of
  *   generating a new value when the request parameter holds a non-null value. Note that if the
  *   hash value of the specified refresh token already exists in Authlete\'s database, the refresh
  *   token cannot be inserted and the `/api/auth/token/create` API will report an error.
  * @param accessTokenPersistent
  *   Get whether the access token expires or not. By default, all access tokens expire after a
  *   period of time determined by their service. If this request parameter is `true`, then the
  *   access token will not automatically expire and must be revoked or deleted manually at the
  *   service. If this request parameter is true, the `accessTokenDuration` request parameter is
  *   ignored.
  * @param certificateThumbprint
  *   The thumbprint of the MTLS certificate bound to this token. If this property is set, a
  *   certificate with the corresponding value MUST be presented with the access token when it is
  *   used by a client. The value of this property must be a SHA256 certificate thumbprint,
  *   base64url encoded.
  * @param dpopKeyThumbprint
  *   The thumbprint of the public key used for DPoP presentation of this token. If this property is
  *   set, a DPoP proof signed with the corresponding private key MUST be presented with the access
  *   token when it is used by a client. Additionally, the token\'s `token_type` will be set to
  *   \'DPoP\'.
  * @param authorizationDetails
  *
  * @param resources
  *   The value of the resources to associate with the token. This property represents the value of
  *   one or more `resource` request parameters which is defined in \"RFC8707 Resource Indicators
  *   for OAuth 2.0\".
  * @param forExternalAttachment
  *   the flag which indicates whether the access token is for an external attachment.
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access token.
  * @param acr
  *   The Authentication Context Class Reference of the user authentication that the authorization
  *   server performed during the course of issuing the access token.
  * @param authTime
  *   The time when the user authentication was performed during the course of issuing the access
  *   token.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class TokenCreateRequest(
    grantType: GrantType,
    clientId: Long,
    subject: Option[String] = None,
    scopes: List[String] = List.empty,
    accessTokenDuration: Option[Long] = None,
    refreshTokenDuration: Option[Long] = None,
    properties: List[Property] = List.empty,
    clientIdAliasUsed: Option[Boolean] = None,
    accessToken: Option[String] = None,
    refreshToken: Option[String] = None,
    accessTokenPersistent: Option[Boolean] = None,
    certificateThumbprint: Option[String] = None,
    dpopKeyThumbprint: Option[String] = None,
    authorizationDetails: Option[AuthzDetails] = None,
    resources: List[String] = List.empty,
    forExternalAttachment: Option[Boolean] = None,
    jwtAtClaims: Option[String] = None,
    acr: Option[String] = None,
    authTime: Option[Long] = None,
    clientEntityIdUsed: Option[Boolean] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenCreateRequest {
  // implicit val codec: JsonValueCodec[TokenCreateRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
