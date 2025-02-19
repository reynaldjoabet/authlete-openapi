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
  */
final case class ClientFlagUpdateResponse(
    resultCode: String,
    resultMessage: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientFlagUpdateResponse {
  // implicit val codec: JsonValueCodec[ClientFlagUpdateResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
