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

  enum DeviceCompleteResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SERVER_ERROR
    case USER_CODE_NOT_EXIST
    case USER_CODE_EXPIRED
    case INVALID_REQUEST
    case SUCCESS

  }

  enum DeviceCompleteResponseErrorResponse
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case SERVER_ERROR
    case USER_CODE_NOT_EXIST
    case USER_CODE_EXPIRED
    case INVALID_REQUEST
    case SUCCESS

  }

  // implicit val codec: JsonValueCodec[DeviceCompleteResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}

object DeviceCompleteResponseAction {
  // implicit val codec
  //     : JsonValueCodec[DeviceCompleteResponse.DeviceCompleteResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
