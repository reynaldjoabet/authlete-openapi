package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  */
final case class ClientGrantedScopesDeleteResponse(
    resultCode: String,
    resultMessage: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientGrantedScopesDeleteResponse {
  // implicit val codec: JsonValueCodec[ClientGrantedScopesDeleteResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
