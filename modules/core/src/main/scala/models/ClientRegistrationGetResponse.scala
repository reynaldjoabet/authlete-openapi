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
  *   The client details returned by the authorization server.
  */
final case class ClientRegistrationGetResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      ClientRegistrationGetResponse.ClientRegistrationGetResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
)

object ClientRegistrationGetResponse {
  enum ClientRegistrationGetResponseAction(value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends ClientRegistrationGetResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends ClientRegistrationGetResponseAction("BAD_REQUEST")
    case Ok extends ClientRegistrationGetResponseAction("OK")
    case Unauthorized
        extends ClientRegistrationGetResponseAction("UNAUTHORIZED")
  }
  implicit val codec: Codec[ClientRegistrationGetResponse] = deriveCodec
}
