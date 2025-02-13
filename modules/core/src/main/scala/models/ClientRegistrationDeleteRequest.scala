package models

import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._

/** @param clientId
  *   The client ID to be deleted.
  * @param token
  *   The client registration access token.
  */
final case class ClientRegistrationDeleteRequest(
    clientId: String,
    token: String
)

object ClientRegistrationDeleteRequest {
  implicit val codec: Codec[ClientRegistrationDeleteRequest] = deriveCodec
}
