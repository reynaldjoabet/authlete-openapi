package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum Sns derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {
  case FACEBOOK
}

object Sns {
  // implicit val codec: JsonValueCodec[Sns] =
  // JsonCodecMaker.make(codecMakerConfig)
}
