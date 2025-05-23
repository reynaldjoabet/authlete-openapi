package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param parameters
  *   OAuth 2.0 token revocation request parameters which are the request parameters that the OAuth
  *   2.0 token revocation endpoint ([RFC 7009](https://datatracker.ietf.org/doc/html/rfc7009)) of
  *   the authorization server implementation received from the client application. The value of
  *   parameters is the entire entity body (which is formatted in
  *   `application/x-www-form-urlencoded`) of the request from the client application.
  * @param clientId
  *   The client ID extracted from `Authorization` header of the revocation request from the client
  *   application. If the revocation endpoint of the authorization server implementation supports
  *   Basic Authentication as a means of client authentication, and the request from the client
  *   application contains its client ID in `Authorization` header, the value should be extracted
  *   and set to this parameter.
  * @param clientSecret
  *   The client secret extracted from `Authorization` header of the revocation request from the
  *   client application. If the revocation endpoint of the authorization server implementation
  *   supports basic authentication as a means of client authentication, and the request from the
  *   client application contained its client secret in `Authorization` header, the value should be
  *   extracted and set to this parameter.
  * @param clientCertificate
  *   The client certificate used in the TLS connection between the client application and the
  *   revocation endpoint.
  * @param clientCertificatePath
  *   The certificate path presented by the client during client authentication.
  */
final case class RevocationRequest(
    parameters: String,
    clientId: Option[String] = None,
    clientSecret: Option[String] = None,
    clientCertificate: Option[String] = None,
    clientCertificatePath: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object RevocationRequest {
  // implicit val codec: JsonValueCodec[RevocationRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
