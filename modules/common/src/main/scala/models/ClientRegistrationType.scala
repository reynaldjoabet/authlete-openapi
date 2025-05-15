package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param value
  *   * Values for the `client_registration_types` RP metadata and the
  *   `client_registration_types_supported` OP metadata that are defined in [OpenID Connect
  *   Federation 1.0](https://openid.net/specs/openid-connect-federation-1_0.html).
  */
enum ClientRegistrationType derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case AUTOMATIC
  case EXPLICIT

}

object ClientRegistrationType {
  // implicit val codec: JsonValueCodec[ClientRegistrationType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
