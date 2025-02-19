package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Represents a grant scope for OAuth 2.0.
  *
  * @param scope
  *   Space-delimited scopes.
  * @param resource
  *   List of resource indicators.
  */
final case class GrantScope(
    scope: Option[String] = None,
    resource: Option[List[String]] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object GrantScope {
  // implicit val codec: JsonValueCodec[GrantScope] =
  // JsonCodecMaker.make(codecMakerConfig)
}
