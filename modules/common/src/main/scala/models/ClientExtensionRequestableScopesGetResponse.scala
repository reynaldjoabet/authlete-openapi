package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param requestableScopes
  *   The scopes that can be requested by the client.
  */
final case class ClientExtensionRequestableScopesGetResponse(
    requestableScopes: Option[Seq[String]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientExtensionRequestableScopesGetResponse {
  // implicit val codec
  //     : JsonValueCodec[ClientExtensionRequestableScopesGetResponse] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
