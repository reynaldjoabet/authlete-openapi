package models

import io.circe.Codec

/** @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  * @param action
  *   The result of the API call.
  * @param hsk
  *   The HS object created, if applicable.
  */

final case class HskCreateResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[HskCreateResponse.HskCreateResponseAction] = None,
    hsk: Option[Hsk] = None
) derives Codec.AsObject

object HskCreateResponse {
  enum HskCreateResponseAction(val value: String) derives Codec.AsObject {
    case Success extends HskCreateResponseAction("SUCCESS")
    case InvalidRequest extends HskCreateResponseAction("INVALID_REQUEST")
    case NotFound extends HskCreateResponseAction("NOT_FOUND")
    case ServerError extends HskCreateResponseAction("SERVER_ERROR")
  }
}
