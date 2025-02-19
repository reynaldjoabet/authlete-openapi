package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param tag
  *   The language tag part.
  * @param value
  *   The value part.
  */
final case class TaggedValue(
    tag: Option[String],
    value: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TaggedValue {
  // implicit val codec: JsonValueCodec[TaggedValue] =
  // JsonCodecMaker.make(codecMakerConfig)
}
