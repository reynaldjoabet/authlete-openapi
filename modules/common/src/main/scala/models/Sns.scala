package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum Sns(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {
  case Facebook extends Sns("FACEBOOK")
}

object Sns {
  // implicit val codec: JsonValueCodec[Sns] =
  // JsonCodecMaker.make(codecMakerConfig)
}
