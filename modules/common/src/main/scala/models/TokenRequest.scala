package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param parameters
  *   OAuth 2.0 token request parameters which are the request parameters that the OAuth 2.0 token
  *   endpoint of the authorization server implementation received from the client application. The
  *   value of parameters is the entire entity body (which is formatted in
  *   `application/x-www-form-urlencoded`) of the request from the client application.
  * @param clientId
  *   The client ID extracted from `Authorization` header of the token request from the client
  *   application. If the token endpoint of the authorization server implementation supports basic
  *   authentication as a means of client authentication, and the request from the client
  *   application contained its client ID in `Authorization` header, the value should be extracted
  *   and set to this parameter.
  * @param clientSecret
  *   The client secret extracted from `Authorization` header of the token request from the client
  *   application. If the token endpoint of the authorization server implementation supports basic
  *   authentication as a means of client authentication, and the request from the client
  *   application contained its client secret in `Authorization` header, the value should be
  *   extracted and set to this parameter.
  * @param clientCertificate
  *   The client certificate from the MTLS of the token request from the client application.
  * @param clientCertificatePath
  *   The certificate path presented by the client during client authentication. These certificates
  *   are strings in PEM format.
  * @param properties
  *   Extra properties to associate with an access token. See [Extra
  *   Properties](https://www.authlete.com/developers/definitive_guide/extra_properties/) for
  *   details.
  * @param dpop
  *   `DPoP` header presented by the client during the request to the token endpoint. The header
  *   contains a signed JWT which includes the public key that is paired with the private key used
  *   to sign the JWT. See [OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer
  *   (DPoP)](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-dpop) for details.
  * @param htm
  *   HTTP method of the token request. This field is used to validate the `DPoP` header. In normal
  *   cases, the value is `POST`. When this parameter is omitted, `POST` is used as the default
  *   value. See [OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer
  *   (DPoP)](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-dpop) for details.
  * @param htu
  *   URL of the token endpoint. This field is used to validate the `DPoP` header. If this parameter
  *   is omitted, the `tokenEndpoint` property of the Service is used as the default value. See
  *   [OAuth 2.0 Demonstration of Proof-of-Possession at the Application Layer
  *   (DPoP)](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-dpop) for details.
  * @param accessToken
  *   The representation of an access token that may be issued as a result of the Authlete API call.
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access token.
  */
final case class TokenRequest(
    parameters: String,
    clientId: Option[String],
    clientSecret: Option[String],
    clientCertificate: Option[String],
    clientCertificatePath: Option[String],
    properties: Option[String],
    dpop: Option[String],
    htm: Option[String],
    htu: Option[String],
    accessToken: Option[String],
    jwtAtClaims: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenRequest {
  // implicit val codec: JsonValueCodec[TokenRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
