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
  *   the client application. Its format varies depending on the value of
  *   `action` parameter.
  */
final case class BackchannelAuthenticationFailResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[
      BackchannelAuthenticationFailResponse.BackchannelAuthenticationFailResponseAction
    ],
    responseContent: Option[String]
) derives Codec.AsObject

object BackchannelAuthenticationFailResponse {

  enum BackchannelAuthenticationFailResponseAction(value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends BackchannelAuthenticationFailResponseAction(
          "INTERNAL_SERVER_ERROR"
        )
    case BadRequest
        extends BackchannelAuthenticationFailResponseAction("BAD_REQUEST")
    case Forbidden
        extends BackchannelAuthenticationFailResponseAction("FORBIDDEN")
  }
}
