package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Supported attachment types. This property corresponds to the `attachments_supported` server
  * metadata which was added by the third implementer\'s draft of OpenID Connect for Identity
  * Assurance 1.0.
  *
  * @param value
  */
enum AttachmentType derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case EMBEDDED
  case EXTERNAL

}

object AttachmentType {
  // implicit val codec: JsonValueCodec[AttachmentType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
