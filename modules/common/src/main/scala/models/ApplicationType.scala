package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The application type. The value of this property affects the validation steps for a redirect
  * URI. See the description about `redirectUris` property for more details.
  */

enum ApplicationType(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Web    extends ApplicationType("WEB")
  case Native extends ApplicationType("NATIVE")

}

object ApplicationType {
  // implicit val codec: JsonValueCodec[ApplicationType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
