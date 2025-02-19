package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Represents a named URI.
  * @param name
  *   The name associated with the URI.
  * @param uri
  *   The URI itself.
  */
final case class NamedUri(
    name: Option[String] = None,
    uri: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object NamedUri {
  // implicit val codec: JsonValueCodec[NamedUri] =
  // JsonCodecMaker.make(codecMakerConfig)
}
