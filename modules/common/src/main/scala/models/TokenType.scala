package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The grant type of the access token when the access token was created.
  *
  * @param value
  */
enum TokenType(val value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case UrnietfparamsoauthtokenTypejwt extends TokenType("urn:ietf:params:oauth:token-type:jwt")

  case UrnietfparamsoauthtokenTypeaccessToken
      extends TokenType("urn:ietf:params:oauth:token-type:access_token")

  case UrnietfparamsoauthtokenTyperefreshToken
      extends TokenType("urn:ietf:params:oauth:token-type:refresh_token")

  case UrnietfparamsoauthtokenTypeidToken
      extends TokenType("urn:ietf:params:oauth:token-type:id_token")

  case UrnietfparamsoauthtokenTypesaml1 extends TokenType("urn:ietf:params:oauth:token-type:saml1")
  case UrnietfparamsoauthtokenTypesaml2 extends TokenType("urn:ietf:params:oauth:token-type:saml2")
  case DeviceCode                       extends TokenType("DEVICE_CODE")
  case TokenExchange                    extends TokenType("TOKEN_EXCHANGE")
  case JwtBearer                        extends TokenType("JWT_BEARER")

  override def toString(): String = value

}

object TokenType {
  // implicit val codec: JsonValueCodec[TokenType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
