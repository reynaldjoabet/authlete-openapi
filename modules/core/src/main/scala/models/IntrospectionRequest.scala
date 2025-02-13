package models

/** @param token
  *   The access token to introspect.
  * @param scopes
  *   The list of scopes required by the caller.
  * @param subject
  *   The subject associated with the access token.
  * @param clientCertificate
  *   The client certificate in PEM format.
  * @param dpop
  *   The DPoP header presented by the client.
  * @param htm
  *   The HTTP method of the request.
  * @param htu
  *   The URL of the protected resource endpoint.
  * @param resources
  *   The resources specified by the resource request parameters.
  * @param acrValues
  *   The authentication context class reference values.
  * @param maxAge
  *   The maximum allowable elapsed time since the user authentication.
  * @param requiredComponents
  *   The HTTP message components required to be in the signature.
  * @param uri
  *   The full URL of the userinfo endpoint.
  * @param message
  *   The HTTP message body of the request, if present.
  * @param headers
  *   The HTTP headers to be included in processing the signature.
  */
final case class IntrospectionRequest(
    token: String,
    scopes: Option[List[String]] = None,
    subject: Option[String] = None,
    clientCertificate: Option[String] = None,
    dpop: Option[String] = None,
    htm: Option[String] = None,
    htu: Option[String] = None,
    resources: Option[List[String]] = None,
    acrValues: Option[List[String]] = None,
    maxAge: Option[Int] = None,
    requiredComponents: Option[List[String]] = None,
    uri: Option[String] = None,
    message: Option[String] = None,
    headers: Option[List[Pair]] = None
)
