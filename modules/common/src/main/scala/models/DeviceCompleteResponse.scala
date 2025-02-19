package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Represents the response after completing the device verification.
  * @param resultCode
  *   The code representing the result of the API call. This provides a status indication of the
  *   request's success or failure.
  * @param resultMessage
  *   A short message explaining the result of the API call. It provides more context on the
  *   outcome.
  * @param action
  *   The next action that the authorization server implementation should take based on the result.
  */
final case class DeviceCompleteResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[DeviceCompleteResponse.DeviceCompleteResponseAction]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object DeviceCompleteResponse {

  enum DeviceCompleteResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case ServerError      extends DeviceCompleteResponseAction("SERVER_ERROR")
    case UserCodeNotExist extends DeviceCompleteResponseAction("USER_CODE_NOT_EXIST")
    case UserCodeExpired  extends DeviceCompleteResponseAction("USER_CODE_EXPIRED")
    case InvalidRequest   extends DeviceCompleteResponseAction("INVALID_REQUEST")
    case Success          extends DeviceCompleteResponseAction("SUCCESS")

  }

  // implicit val codec: JsonValueCodec[DeviceCompleteResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum DeviceCompleteResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case ServerError      extends DeviceCompleteResponseErrorResponse("SERVER_ERROR")
    case UserCodeNotExist extends DeviceCompleteResponseErrorResponse("USER_CODE_NOT_EXIST")
    case UserCodeExpired  extends DeviceCompleteResponseErrorResponse("USER_CODE_EXPIRED")
    case InvalidRequest   extends DeviceCompleteResponseErrorResponse("INVALID_REQUEST")
    case Success          extends DeviceCompleteResponseErrorResponse("SUCCESS")

  }

}

object DeviceCompleteResponseAction {
  // implicit val codec
  //     : JsonValueCodec[DeviceCompleteResponse.DeviceCompleteResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
