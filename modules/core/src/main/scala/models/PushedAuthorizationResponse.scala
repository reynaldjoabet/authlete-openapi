package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server should take. Any value other
  *   than "CREATED" should be handled as an unsuccessful result.
  * @param requestUri
  *   The request_uri created for the client to be used in the authorize call.
  * @param responseContent
  *   The content that the authorization server implementation should return to
  *   the client application.
  */
final case class PushedAuthorizationResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      PushedAuthorizationResponse.PushedAuthorizationResponseAction
    ] = None,
    requestUri: Option[String] = None,
    responseContent: Option[String] = None
) derives Codec.AsObject

object PushedAuthorizationResponse {
  enum PushedAuthorizationResponseAction(val value: String)
      derives Codec.AsObject {
    case Created extends PushedAuthorizationResponseAction("CREATED")
    case BadRequest extends PushedAuthorizationResponseAction("BAD_REQUEST")
    case Unauthorized extends PushedAuthorizationResponseAction("UNAUTHORIZED")
    case Forbidden extends PushedAuthorizationResponseAction("FORBIDDEN")
    case PayloadTooLarge
        extends PushedAuthorizationResponseAction("PAYLOAD_TOO_LARGE")
    case InternalServerError
        extends PushedAuthorizationResponseAction("INTERNAL_SERVER_ERROR")
  }
}
