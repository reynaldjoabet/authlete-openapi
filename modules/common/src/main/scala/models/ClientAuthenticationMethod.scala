package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The client authentication method that the client application declares that it uses at the token
  * endpoint. This property corresponds to `token_endpoint_auth_method` in [OpenID Connect Dynamic
  * Client Registration 1.0, 2. Client
  * Metadata](https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata).
  *
  * @param value
  */
enum ClientAuthenticationMethod(value: String)
    derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject {

  case None                    extends ClientAuthenticationMethod("NONE")
  case ClientSecretBasic       extends ClientAuthenticationMethod("CLIENT_SECRET_BASIC")
  case ClientSecretPost        extends ClientAuthenticationMethod("CLIENT_SECRET_POST")
  case ClientSecretJwt         extends ClientAuthenticationMethod("CLIENT_SECRET_JWT")
  case PrivateKeyJwt           extends ClientAuthenticationMethod("PRIVATE_KEY_JWT")
  case TlsClientAuth           extends ClientAuthenticationMethod("TLS_CLIENT_AUTH")
  case SelfSignedTlsClientAuth extends ClientAuthenticationMethod("SELF_SIGNED_TLS_CLIENT_AUTH")

}

object ClientAuthenticationMethod {
  // implicit val codec: JsonValueCodec[ClientAuthenticationMethod] =
  // JsonCodecMaker.make(codecMakerConfig)
}
