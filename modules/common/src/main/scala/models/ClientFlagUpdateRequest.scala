package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param clientLocked
  *   The flag value to be set
  */
final case class ClientFlagUpdateRequest(
    clientLocked: Boolean
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientFlagUpdateRequest {
  // implicit val codec: JsonValueCodec[ClientFlagUpdateRequest] =
  // JsonCodecMaker.make(codecMakerConfig)

  implicit val clientFlagUpdateRequestCodec: JsonValueCodec[Option[ClientFlagUpdateRequest]] =
    JsonCodecMaker.make(codecMakerConfig)

}
