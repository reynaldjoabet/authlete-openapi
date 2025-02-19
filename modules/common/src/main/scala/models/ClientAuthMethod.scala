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
enum ClientAuthMethod(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case None                    extends ClientAuthMethod("NONE")
  case ClientSecretBasic       extends ClientAuthMethod("CLIENT_SECRET_BASIC")
  case ClientSecretPost        extends ClientAuthMethod("CLIENT_SECRET_POST")
  case ClientSecretJwt         extends ClientAuthMethod("CLIENT_SECRET_JWT")
  case PrivateKeyJwt           extends ClientAuthMethod("PRIVATE_KEY_JWT")
  case TlsClientAuth           extends ClientAuthMethod("TLS_CLIENT_AUTH")
  case SelfSignedTlsClientAuth extends ClientAuthMethod("SELF_SIGNED_TLS_CLIENT_AUTH")

}

object ClientAuthMethod {
  // implicit val codec: JsonValueCodec[ClientAuthMethod] =
  // JsonCodecMaker.make(codecMakerConfig)
}
