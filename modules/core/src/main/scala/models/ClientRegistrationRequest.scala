package models

import io.circe.Codec
import io.circe.generic.semiauto._

/** @param json
  *   The client metadata in JSON format that complies with [RFC
  *   7591](https://datatracker.ietf.org/doc/html/rfc7591) (OAuth 2.0 Dynamic
  *   Client Registration Protocol).
  * @param token
  *   The client registration access token, used only for GET, UPDATE, and
  *   DELETE requests.
  * @param clientId
  *   The client's identifier, used for GET, UPDATE, and DELETE requests.
  */
final case class ClientRegistrationRequest(
    json: String,
    token: Option[String] = None,
    clientId: Option[String] = None
)

object ClientRegistrationRequest {
  implicit val codec: Codec[ClientRegistrationRequest] = deriveCodec
}
