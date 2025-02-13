package models

import io.circe.Codec

/** @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  * @param action
  *   The result of the API call.
  * @param hsk
  *   The retrieved HS object, if applicable.
  */
final case class HskGetResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[HskGetResponse.HskGetResponseAction] = None,
    hsk: Option[Hsk] = None
) derives Codec.AsObject

object HskGetResponse {

  enum HskGetResponseAction(val value: String) derives Codec.AsObject {
    case Success extends HskGetResponseAction("SUCCESS")
    case InvalidRequest extends HskGetResponseAction("INVALID_REQUEST")
    case NotFound extends HskGetResponseAction("NOT_FOUND")
    case ServerError extends HskGetResponseAction("SERVER_ERROR")
  }
}
