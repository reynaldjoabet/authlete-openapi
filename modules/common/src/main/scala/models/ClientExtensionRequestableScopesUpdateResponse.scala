package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param requestableScopes
  */
final case class ClientExtensionRequestableScopesUpdateResponse(
    requestableScopes: Option[Seq[String]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientExtensionRequestableScopesUpdateResponse {
  // implicit val codec
  //     : JsonValueCodec[ClientExtensionRequestableScopesUpdateResponse] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
