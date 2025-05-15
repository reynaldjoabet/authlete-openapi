package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum ClaimType derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case NORMAL
  case AGGREGATED
  case ISTRIBUTED

}

object ClaimType {
  // implicit val codec: JsonValueCodec[ClaimType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
