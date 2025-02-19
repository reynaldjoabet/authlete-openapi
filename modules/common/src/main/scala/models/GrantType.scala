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

enum GrantType(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case AuthorizationCode extends GrantType("AUTHORIZATION_CODE")
  case Implicit          extends GrantType("IMPLICIT")
  case Password          extends GrantType("PASSWORD")
  case ClientCredentials extends GrantType("CLIENT_CREDENTIALS")
  case RefreshToken      extends GrantType("REFRESH_TOKEN")
  case Ciba              extends GrantType("CIBA")
  case DeviceCode        extends GrantType("DEVICE_CODE")
  case TokenExchange     extends GrantType("TOKEN_EXCHANGE")
  case JwtBearer         extends GrantType("JWT_BEARER")

}

object GrantType {
  // implicit val codec: JsonValueCodec[GrantType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
