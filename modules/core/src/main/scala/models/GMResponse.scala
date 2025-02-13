package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to
  *   the client application.
  */
final case class GMResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[GMResponse.GMResponseAction] = None,
    responseContent: Option[String] = None
) derives Codec.AsObject

object GMResponse {

  enum GMResponseAction(val value: String) derives Codec.AsObject {

    case Ok extends GMResponseAction("OK")

    case NoContent extends GMResponseAction("NO_CONTENT")

    case Unauthorized extends GMResponseAction("UNAUTHORIZED")

    case Forbidden extends GMResponseAction("FORBIDDEN")

    case NotFound extends GMResponseAction("NOT_FOUND")

    case CallerError extends GMResponseAction("CALLER_ERROR")

    case AuthleteError extends GMResponseAction("AUTHLETE_ERROR")
  }

}
