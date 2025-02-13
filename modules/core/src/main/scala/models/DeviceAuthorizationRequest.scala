package models

import io.circe._
import io.circe.generic.semiauto._

/** @param parameters
  *   The entire entity body (formatted as `application/x-www-form-urlencoded`)
  *   of the request from the client application.
  * @param clientId
  *   The client ID extracted from the `Authorization` header, optional.
  * @param clientSecret
  *   The client secret extracted from the `Authorization` header, optional.
  * @param clientCertificate
  *   The client certificate used in the TLS connection between the client and
  *   the server, optional.
  * @param clientCertificatePath
  *   The client certificate path presented during client authentication,
  *   optional.
  */
final case class DeviceAuthorizationRequest(
    parameters: String,
    clientId: Option[String] = None,
    clientSecret: Option[String] = None,
    clientCertificate: Option[String] = None,
    clientCertificatePath: Option[String] = None
)

object DeviceAuthorizationRequest {
  implicit val codec: Codec[DeviceAuthorizationRequest] = deriveCodec
}
