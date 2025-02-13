package models

import io.circe.{Codec, Decoder, Encoder}
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
  *   The client associated with the response.
  */
final case class ClientRegistrationDeleteResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      ClientRegistrationDeleteResponse.ClientRegistrationDeleteResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
)

object ClientRegistrationDeleteResponse {

  enum ClientRegistrationDeleteResponseAction(val value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends ClientRegistrationDeleteResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest
        extends ClientRegistrationDeleteResponseAction("BAD_REQUEST")
    case Deleted extends ClientRegistrationDeleteResponseAction("DELETED")
    case Unauthorized
        extends ClientRegistrationDeleteResponseAction("UNAUTHORIZED")
  }
  implicit val codec: Codec[ClientRegistrationDeleteResponse] = deriveCodec
}
