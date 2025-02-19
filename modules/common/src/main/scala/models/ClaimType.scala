package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum ClaimType(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Normal      extends ClaimType("NORMAL")
  case Aggregated  extends ClaimType("AGGREGATED")
  case Distributed extends ClaimType("DISTRIBUTED")

}

object ClaimType {
  // implicit val codec: JsonValueCodec[ClaimType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
