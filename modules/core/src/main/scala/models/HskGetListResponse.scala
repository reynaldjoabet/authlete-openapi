package models

import io.circe.Codec
import io.circe.generic.semiauto._

import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._

/** @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  * @param action
  *   The result of the API call.
  * @param hsks
  *   The list of HSK objects.
  */

final case class HskGetListResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[HskGetListResponse.HskGetListResponseAction] = None,
    hsks: Option[List[Hsk]] = None
) derives Codec.AsObject

object HskGetListResponse {
  enum HskGetListResponseAction(val value: String) derives Codec.AsObject {
    case Success extends HskGetListResponseAction("SUCCESS")
    case InvalidRequest extends HskGetListResponseAction("INVALID_REQUEST")
    case ServerError extends HskGetListResponseAction("SERVER_ERROR")
  }
}
