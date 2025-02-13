package models

import io.circe.Codec

/** @param parameters
  *   Parameters of a backchannel authentication request which are the request
  *   parameters that the backchannel authentication endpoint of the OpenID
  *   provider implementation received from the client application. The value of
  *   `parameters` is the entire entity body (which is formatted in
  *   `application/x-www-form-urlencoded`) of the request from the client
  *   application.
  * @param clientId
  *   The client ID extracted from Authorization header of the backchannel
  *   authentication request from the client application. If the backchannel
  *   authentication endpoint of the OpenID provider implementation supports
  *   Basic Authentication as a means of client authentication, and the request
  *   from the client application contained its client ID in Authorization
  *   header, the value should be extracted and set to this parameter.
  * @param clientSecret
  *   The client secret extracted from Authorization header of the backchannel
  *   authentication request from the client application. If the backchannel
  *   authentication endpoint of the OpenID provider implementation supports
  *   Basic Authentication as a means of client authentication, and the request
  *   from the client application contained its client secret in Authorization
  *   header, the value should be extracted and set to this parameter.
  * @param clientCertificate
  *   The client certification used in the TLS connection between the client
  *   application and the backchannel authentication endpoint of the OpenID
  *   provider.
  * @param clientCertificatePath
  *   The client certificate path presented by the client during client
  *   authentication. Each element is a string in PEM format.
  */
final case class BackchannelAuthenticationRequest(
    parameters: String,
    clientId: Option[String] = None,
    clientSecret: Option[String] = None,
    clientCertificate: Option[String] = None,
    clientCertificatePath: Option[String] = None
) derives Codec.AsObject
