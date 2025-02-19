package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param entityId
  *   the entity ID of the trust anchor
  * @param jwks
  *   the JWK Set document containing public keys of the trust anchor
  */
final case class TrustAnchor(
    entityId: Option[String] = None,
    jwks: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TrustAnchor {
  // implicit val codec: JsonValueCodec[TrustAnchor] =
  // JsonCodecMaker.make(codecMakerConfig)
}
