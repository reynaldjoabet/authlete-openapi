package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   The code which represents the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to
  *   the client application. Its format varies depending on the value of
  *   `action` parameter.
  */
final case class TokenFailResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[TokenFailResponse.TokenFailResponseAction],
    responseContent: Option[String]
) derives Codec.AsObject

object TokenFailResponse {

  enum TokenFailResponseAction(value: String) derives Codec.AsObject {
    case InternalServerError
        extends TokenFailResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends TokenFailResponseAction("BAD_REQUEST")
  }
}
