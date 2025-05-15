package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum ResponseType derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case NONE
  case CODE
  case TOKEN
  case ID_TOKEN
  case CODE_TOKEN
  case CODE_ID_TOKEN
  case ID_TOKEN_TOKEN
  case CODE_ID_TOKEN_TOKEN

}

object ResponseType {
  // implicit val codec: JsonValueCodec[ResponseType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
