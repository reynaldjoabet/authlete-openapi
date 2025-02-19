package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Represents a dynamic scope.
  *
  * @param name
  *   The scope name.
  * @param value
  *   The scope value.
  */
final case class DynamicScope(
    name: Option[String] = None,
    value: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object DynamicScope {
  // implicit val codec: JsonValueCodec[DynamicScope] =
  // JsonCodecMaker.make(codecMakerConfig)
}
