package models

import io.circe.Codec
import io.circe.generic.semiauto._

/** @param clientId
  *   The client ID.
  * @param token
  *   The client registration access token.
  */
final case class ClientRegistrationGetRequest(
    clientId: String,
    token: String
)

object ClientRegistrationGetRequest {
  implicit val codec: Codec[ClientRegistrationGetRequest] = deriveCodec

}
