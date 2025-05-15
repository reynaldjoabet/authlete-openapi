package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param accessToken
  *   An access token.
  * @param accessTokenExpiresAt
  *   A new date at which the access token will expire in milliseconds since the Unix epoch
  *   (1970-01-01). If the `accessTokenExpiresAt` request parameter is not included in a request or
  *   its value is 0 (or negative), the expiration date of the access token is not changed.
  * @param scopes
  *   A new set of scopes assigned to the access token. Scopes that are not supported by the service
  *   and those that the client application associated with the access token is not allowed to
  *   request are ignored on the server side. If the `scopes` request parameter is not included in a
  *   request or its value is `null`, the scopes of the access token are not changed. Note that
  *   `properties` parameter is accepted only when `Content-Type` of the request is
  *   `application/json`, so don\'t use `application/x-www-form-urlencoded` if you want to specify
  *   `properties`.
  * @param properties
  *   A new set of properties assigned to the access token. If the `properties` request parameter is
  *   not included in a request or its value is null, the properties of the access token are not
  *   changed.
  * @param accessTokenExpiresAtUpdatedOnScopeUpdate
  *   A boolean request parameter which indicates whether the API attempts to update the expiration
  *   date of the access token when the scopes linked to the access token are changed by this
  *   request.
  * @param accessTokenHash
  *   The hash of the access token value. Used when the hash of the token is known (perhaps from
  *   lookup) but the value of the token itself is not. The value of the `accessToken` parameter
  *   takes precedence.
  * @param accessTokenValueUpdated
  *   A boolean request parameter which indicates whether to update the value of the access token in
  *   the data store. If this parameter is set to `true` then a new access token value is generated
  *   by the server and returned in the response.
  * @param accessTokenPersistent
  *   The flag which indicates whether the access token expires or not. By default, all access
  *   tokens expire after a period of time determined by their service. If this request parameter is
  *   `true` then the access token will not automatically expire and must be revoked or deleted
  *   manually at the service. If this request parameter is `true`, the `accessTokenExpiresAt`
  *   request parameter is ignored. If this request parameter is `false`, the `accessTokenExpiresAt`
  *   request parameter is processed normally.
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
  * @param forExternalAttachment
  *   the flag which indicates whether the access token is for an external attachment.
  */
final case class TokenUpdateRequest(
    accessToken: String,
    accessTokenExpiresAt: Option[Long],
    scopes: List[String] = List.empty,
    properties: List[Property] = List.empty,
    accessTokenExpiresAtUpdatedOnScopeUpdate: Option[Boolean],
    accessTokenHash: Option[String],
    accessTokenValueUpdated: Option[Boolean],
    accessTokenPersistent: Option[Boolean],
    certificateThumbprint: Option[String],
    dpopKeyThumbprint: Option[String],
    authorizationDetails: Option[AuthzDetails],
    forExternalAttachment: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenUpdateRequest {
  // implicit val codec: JsonValueCodec[TokenUpdateRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
