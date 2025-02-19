package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Represents a key-value pair.
  * @param key
  *   The key part.
  * @param value
  *   The value part.
  */
final case class Pair(
    key: Option[String] = None,
    value: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object Pair {
  // implicit val codec: JsonValueCodec[Pair] =
  // JsonCodecMaker.make(codecMakerConfig)
}
