package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum ServiceProfile(val value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Fapi        extends ServiceProfile("FAPI")
  case OpenBanking extends ServiceProfile("OPEN_BANKING")

}

object ServiceProfile {
  // implicit val codec: JsonValueCodec[ServiceProfile] =
  // JsonCodecMaker.make(codecMakerConfig)
}
