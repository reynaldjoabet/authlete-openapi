package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

enum ResponseType(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case None             extends ResponseType("NONE")
  case Code             extends ResponseType("CODE")
  case Token            extends ResponseType("TOKEN")
  case IdToken          extends ResponseType("ID_TOKEN")
  case CodeToken        extends ResponseType("CODE_TOKEN")
  case CodeIdToken      extends ResponseType("CODE_ID_TOKEN")
  case IdTokenToken     extends ResponseType("ID_TOKEN_TOKEN")
  case CodeIdTokenToken extends ResponseType("CODE_ID_TOKEN_TOKEN")

}

object ResponseType {
  // implicit val codec: JsonValueCodec[ResponseType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
