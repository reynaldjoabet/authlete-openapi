package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to
  *   the client application. Its format varies depending on the value of
  *   `action` parameter.
  */
final case class RevocationResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[RevocationResponse.RevocationResponseAction],
    responseContent: Option[String]
) derives Codec.AsObject

object RevocationResponse {
  enum RevocationResponseAction(value: String) derives Codec.AsObject {
    case InternalServerError
        extends RevocationResponseAction("INTERNAL_SERVER_ERROR")
    case InvalidClient extends RevocationResponseAction("INVALID_CLIENT")
    case BadRequest extends RevocationResponseAction("BAD_REQUEST")
    case Ok extends RevocationResponseAction("OK")
  }
}
