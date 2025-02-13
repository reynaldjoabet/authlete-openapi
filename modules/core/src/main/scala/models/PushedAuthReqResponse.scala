package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  *   Any other value other than `CREATED` should be handled as unsuccessful
  *   result.
  * @param requestUri
  *   The request_uri created for the client to be used in the authorize call.
  * @param responseContent
  *   The content that the authorization server implementation should return to
  *   the client application.
  */
final case class PushedAuthReqResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[PushedAuthReqResponse.PushedAuthReqResponseAction] = None,
    requestUri: Option[String] = None,
    responseContent: Option[String] = None
) derives Codec.AsObject

object PushedAuthReqResponse {

  enum PushedAuthReqResponseAction(val value: String) derives Codec.AsObject {
    case Created extends PushedAuthReqResponseAction("CREATED")
    case BadRequest extends PushedAuthReqResponseAction("BAD_REQUEST")
    case Unauthorized extends PushedAuthReqResponseAction("UNAUTHORIZED")
    case Forbidden extends PushedAuthReqResponseAction("FORBIDDEN")
    case PayloadTooLarge
        extends PushedAuthReqResponseAction("PAYLOAD_TOO_LARGE")
    case InternalServerError
        extends PushedAuthReqResponseAction("INTERNAL_SERVER_ERROR")
  }

}
