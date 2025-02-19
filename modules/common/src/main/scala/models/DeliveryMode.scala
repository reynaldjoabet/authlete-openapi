package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum DeliveryMode(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Ping extends DeliveryMode("PING")
  case Poll extends DeliveryMode("POLL")
  case Push extends DeliveryMode("PUSH")

}

object DeliveryMode {
  // implicit val codec: JsonValueCodec[DeliveryMode] =
  // JsonCodecMaker.make(codecMakerConfig)
}
