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
  * @param newClientSecret
  *   The new client secret.
  * @param oldClientSecret
  *   The old client secret.
  */
final case class ClientSecretRefreshResponse(
    resultCode: String,
    resultMessage: String,
    newClientSecret: Option[String] = None,
    oldClientSecret: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientSecretRefreshResponse {
  // implicit val codec: JsonValueCodec[ClientSecretRefreshResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
