package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param version
  *   The server version.
  * @param features
  *   The list of features that the server supports.
  */
final case class InfoResponse(
    version: String,
    features: List[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object InfoResponse {
  // implicit val codec: JsonValueCodec[InfoResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
