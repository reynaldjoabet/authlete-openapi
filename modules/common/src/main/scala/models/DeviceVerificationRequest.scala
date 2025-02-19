package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param userCode
  *   A user code that is used for device verification. This is the code that the user has entered
  *   on the device for authentication.
  */
final case class DeviceVerificationRequest(userCode: String)
    derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object DeviceVerificationRequest {
  // implicit val codec: JsonValueCodec[DeviceVerificationRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
