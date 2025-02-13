package models

import io.circe.Codec

/** Represents the response after completing the device verification.
  * @param resultCode
  *   The code representing the result of the API call. This provides a status
  *   indication of the request's success or failure.
  * @param resultMessage
  *   A short message explaining the result of the API call. It provides more
  *   context on the outcome.
  * @param action
  *   The next action that the authorization server implementation should take
  *   based on the result.
  */
final case class DeviceCompleteResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[DeviceCompleteResponse.DeviceCompleteResponseAction]
) derives Codec.AsObject

object DeviceCompleteResponse {
  enum DeviceCompleteResponseAction(val value: String) derives Codec.AsObject {
    case ServerError extends DeviceCompleteResponseAction("SERVER_ERROR")
    case UserCodeNotExist
        extends DeviceCompleteResponseAction("USER_CODE_NOT_EXIST")
    case UserCodeExpired
        extends DeviceCompleteResponseAction("USER_CODE_EXPIRED")
    case InvalidRequest extends DeviceCompleteResponseAction("INVALID_REQUEST")
    case Success extends DeviceCompleteResponseAction("SUCCESS")
  }
}
