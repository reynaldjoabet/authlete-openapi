package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param clientId
  *   The client id.
  * @param clientIdAlias
  *   The alias of the client.
  * @param clientIdAliasUsed
  *   Flag specifying if the alias was used to identify the client
  * @param subject
  *   the resource owner unique id
  * @param scopes
  *   The scopes granted on the token
  * @param expiresAt
  *   time which the token expires.
  * @param properties
  *   Extra properties associated with the token
  * @param resources
  *   The array of the resources of the token.
  * @param authorizationDetails
  *
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class TokenInfo(
    clientId: Option[Long],
    clientIdAlias: Option[String],
    clientIdAliasUsed: Option[Boolean],
    subject: Option[String],
    scopes: Option[List[String]],
    expiresAt: Option[Long],
    properties: Option[List[Property]],
    resources: Option[List[String]],
    authorizationDetails: Option[AuthzDetails],
    clientEntityId: Option[String],
    clientEntityIdUsed: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenInfo {
  // implicit val codec: JsonValueCodec[TokenInfo] =
  // JsonCodecMaker.make(codecMakerConfig)
}
