package models

import io.circe.Codec
import io.circe.generic.semiauto._

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to
  *   the client application.
  * @param client
  *   The client associated with the registration.
  */
final case class ClientRegistrationResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      ClientRegistrationResponse.ClientRegistrationResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives Codec.AsObject

object ClientRegistrationResponse {
  enum ClientRegistrationResponseAction(val value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends ClientRegistrationResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends ClientRegistrationResponseAction("BAD_REQUEST")
    case Created extends ClientRegistrationResponseAction("CREATED")
  }
}
