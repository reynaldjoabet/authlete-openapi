package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The grant type of the access token when the access token was created.
  * @param value
  */

enum GrantType derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case AUTHORIZATION_CODE
  case IMPLICIT
  case PASSWORD
  case CLIENT_CREDENTIALS
  case REFRESH_TOKEN
  case CIBA
  case DEVICE_CODE
  case TOKEN_EXCHANGE
  case JWT_BEARER

}

object GrantType {
  // implicit val codec: JsonValueCodec[GrantType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
