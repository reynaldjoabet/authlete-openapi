package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

final case class VerifiedClaimsValidationSchema()
    derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object VerifiedClaimsValidationSchema {
  // implicit val codec: JsonValueCodec[VerifiedClaimsValidationSchema] =
  // JsonCodecMaker.make(codecMakerConfig)
}
