package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.*
import io.circe.generic.semiauto.*
import sttp.tapir.Schema

/**
  * When the authorization request does not have a `display` request parameter, the default value
  * `PAGE` is used. If the display mode specified in the authorization request is not supported, an
  * error is raised.
  *
  * Values for this property are listed in "[OpenID Connect Core 1.0, 3.1.2.1. Authentication
  * Request](https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest)".
  * @param value
  */
enum Display derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case PAGE
  case POPUP
  case TOUCH
  case WAP

}

object Display {
  // implicit val codec: JsonValueCodec[Display] =
  // JsonCodecMaker.make(codecMakerConfig)
}
