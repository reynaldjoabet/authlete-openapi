package models

import io.circe.Codec
import io.circe.generic.semiauto._

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
  * @param hsk
  *   The HS object deleted, if applicable.
  */

final case class HskDeleteResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[HskDeleteResponse.HskDeleteResponseAction] = None,
    hsk: Option[Hsk] = None
) derives Codec.AsObject

object HskDeleteResponse {
  enum HskDeleteResponseAction(val value: String) derives Codec.AsObject {
    case Success extends HskDeleteResponseAction("SUCCESS")
    case InvalidRequest extends HskDeleteResponseAction("INVALID_REQUEST")
    case NotFound extends HskDeleteResponseAction("NOT_FOUND")
    case ServerError extends HskDeleteResponseAction("SERVER_ERROR")
  }
}
