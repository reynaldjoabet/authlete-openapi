package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param sns
  *   SNS.
  * @param apiKey
  *   API key.
  * @param apiSecret
  *   API secret.
  */
final case class SnsCredentials(
    sns: Option[String],
    apiKey: Option[String],
    apiSecret: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object SnsCredentials {
  // implicit val codec: JsonValueCodec[SnsCredentials] =
  // JsonCodecMaker.make(codecMakerConfig)
}
