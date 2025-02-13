package models

import io.circe.Codec

/** @param parameters
  *   The pushed authorization request body received from the client
  *   application, formatted in `application/x-www-form-urlencoded`.
  * @param clientId
  *   The client ID extracted from the `Authorization` header of the pushed
  *   request.
  * @param clientSecret
  *   The client secret extracted from the `Authorization` header of the pushed
  *   authorization request.
  * @param clientCertificate
  *   The client certificate from the MTLS connection to the pushed
  *   authorization endpoint.
  * @param clientCertificatePath
  *   The certificate path presented by the client during client authentication,
  *   in PEM format.
  */
final case class PushedAuthReqRequest(
    parameters: String,
    clientId: Option[String] = None,
    clientSecret: Option[String] = None,
    clientCertificate: Option[String] = None,
    clientCertificatePath: Option[String] = None
) derives Codec.AsObject
