package models

import io.circe.Codec

/** @param accessTokenIdentifier
  *   The identifier of an access token to revoke The hash of an access token is
  *   recognized as an identifier as well as the access token itself.
  * @param refreshTokenIdentifier
  *   The identifier of a refresh token to revoke. The hash of a refresh token
  *   is recognized as an identifier as well as the refresh token itself.
  * @param clientIdentifier
  *   The client ID of the access token to be revoked. Both the numeric client
  *   ID and the alias are recognized as an identifier of a client.
  * @param subject
  *   The subject of a resource owner.
  */
final case class TokenRevokeRequest(
    accessTokenIdentifier: Option[String],
    refreshTokenIdentifier: Option[String],
    clientIdentifier: Option[String],
    subject: Option[String]
) derives Codec.AsObject
