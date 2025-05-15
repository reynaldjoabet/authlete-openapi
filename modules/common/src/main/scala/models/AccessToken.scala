package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param accessTokenHash
  *   The hash of the access token.
  * @param accessTokenExpiresAt
  *   The timestamp at which the access token will expire.
  * @param refreshTokenHash
  *   The hash of the refresh token.
  * @param refreshTokenExpiresAt
  *   The timestamp at which the refresh token will expire.
  * @param createdAt
  *   The timestamp at which the access token was first created.
  * @param lastRefreshedAt
  *   The timestamp at which the access token was last refreshed using the refresh token.
  * @param clientId
  *   The ID of the client associated with the access token.
  * @param subject
  *   The subject (= unique user ID) associated with the access token.
  * @param grantType
  *
  * @param scopes
  *   The scopes associated with the access token.
  * @param properties
  *   The properties associated with the access token.
  */
final case class AccessToken(
    accessTokenHash: Option[String],
    accessTokenExpiresAt: Option[Long],
    refreshTokenHash: Option[String],
    refreshTokenExpiresAt: Option[Long],
    createdAt: Option[Long],
    lastRefreshedAt: Option[Long],
    clientId: Option[Long],
    subject: Option[String],
    grantType: Option[GrantType],
    scopes: List[String],
    properties: List[Property]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AccessToken {
  // //implicit val codec: JsonValueCodec[AccessToken] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
