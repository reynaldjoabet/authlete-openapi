package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The character set for end-user verification codes (`user_code`) for Device Flow.
  * @param value
  */
enum UserCodeCharset(val value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Base20  extends UserCodeCharset("BASE20")
  case Numeric extends UserCodeCharset("NUMERIC")

}

object UserCodeCharset {
  // implicit val codec: JsonValueCodec[UserCodeCharset] =
  // JsonCodecMaker.make(codecMakerConfig)
}
