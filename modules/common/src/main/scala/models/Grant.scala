package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param scopes
  * @param claims
  *   The claims associated with the Grant.
  * @param authorizationDetails
  */
final case class Grant(
    scopes: Option[List[GrantScope]] = None,
    claims: Option[List[String]] = None,
    authorizationDetails: Option[AuthzDetails] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object Grant {
  // implicit val codec: JsonValueCodec[Grant] =
  // JsonCodecMaker.make(codecMakerConfig)
}
