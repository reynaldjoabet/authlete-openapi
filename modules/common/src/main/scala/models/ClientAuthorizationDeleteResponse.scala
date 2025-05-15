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
  *   Short message which explains the result of the API call.
  * @param clientId
  *   The client ID.
  * @param serviceApiKey
  *   The API key of the service.
  * @param subject
  *   The subject of the user.
  * @param modifiedAt
  *   The timestamp at which this record was modified.
  * @param latestGrantedScopes
  *   The scopes granted to the client application by the last authorization process.
  * @param mergedGrantedScopes
  *   The scopes granted to the client application by all the past authorization processes.
  */
final case class ClientAuthorizationDeleteResponse(
    resultCode: String,
    resultMessage: String,
    clientId: Option[Long],
    serviceApiKey: Option[Long],
    subject: Option[String],
    modifiedAt: Option[Long],
    latestGrantedScopes: List[String],
    mergedGrantedScopes: List[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientAuthorizationDeleteResponse {
  // implicit val codec: JsonValueCodec[ClientAuthorizationDeleteResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
