package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Supported subject types. This property corresponds to the `subject_types_supported` server
  * metadata which was added by the third implementer\'s draft of OpenID Connect for Identity
  * Assurance 1.0.
  *
  * @param value
  */
enum SubjectType(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Public   extends SubjectType("PUBLIC")
  case Pairwise extends SubjectType("PAIRWISE")

}

object SubjectType {
  // implicit val codec: JsonValueCodec[SubjectType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
