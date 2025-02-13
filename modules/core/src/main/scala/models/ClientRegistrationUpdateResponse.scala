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
  *   The client associated with the update.
  */
final case class ClientRegistrationUpdateResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      ClientRegistrationUpdateResponse.ClientRegistrationUpdateResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives Codec.AsObject

object ClientRegistrationUpdateResponse {
  enum ClientRegistrationUpdateResponseAction(val value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends ClientRegistrationUpdateResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest
        extends ClientRegistrationUpdateResponseAction("BAD_REQUEST")
    case Updated extends ClientRegistrationUpdateResponseAction("UPDATED")
    case Unauthorized
        extends ClientRegistrationUpdateResponseAction("UNAUTHORIZED")
  }
}
