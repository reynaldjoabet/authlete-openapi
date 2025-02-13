package models

import io.circe.Codec

/** @param token
  *   An access token.
  * @param clientCertificate
  *   Client certificate used in the TLS connection established between the
  *   client application and the userinfo endpoint. The value of this request
  *   parameter is referred to when the access token given to the userinfo
  *   endpoint was bound to a client certificate when it was issued. See [OAuth
  *   2.0 Mutual TLS Client Authentication and Certificate-Bound Access Tokens]
  *   (https://datatracker.ietf.org/doc/rfc8705/) for details about the
  *   specification of certificate-bound access tokens.
  * @param dpop
  *   `DPoP` header presented by the client during the request to the user info
  *   endpoint. The header contains a signed JWT which includes the public key
  *   that is paired with the private key used to sign the JWT. See [OAuth 2.0
  *   Demonstration of Proof-of-Possession at the Application Layer
  *   (DPoP)](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-dpop) for
  *   details.
  * @param htm
  *   HTTP method of the user info request. This field is used to validate the
  *   DPoP header. In normal cases, the value is either `GET` or `POST`.
  * @param htu
  *   URL of the user info endpoint. This field is used to validate the DPoP
  *   header. If this parameter is omitted, the `userInfoEndpoint` property of
  *   the service is used as the default value. See [OAuth 2.0 Demonstration of
  *   Proof-of-Possession at the Application Layer
  *   (DPoP)](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-dpop) for
  *   details.
  * @param uri
  *   The full URL of the userinfo endpoint.
  * @param message
  *   The HTTP message body of the request, if present.
  * @param headers
  *   HTTP headers to be included in processing the signature. If this is a
  *   signed request, this must include the Signature and Signature-Input
  *   headers, as well as any additional headers covered by the signature.
  */
final case class UserinfoRequest(
    token: String,
    clientCertificate: Option[String] = None,
    dpop: Option[String] = None,
    htm: Option[String] = None,
    htu: Option[String] = None,
    uri: Option[String] = None,
    message: Option[String] = None,
    headers: Option[Array[Pair]] = None
) derives Codec.AsObject
