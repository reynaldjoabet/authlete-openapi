package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param parameters
  *   The entire entity body (formatted in `application/x-www-form-urlencoded`) of the request.
  * @param clientId
  *   The client ID extracted from `Authorization` header of the pushed request from the client
  *   application.
  * @param clientSecret
  *   The client secret extracted from `Authorization` header of the pushed authorization request
  *   from the client application.
  * @param clientCertificate
  *   The client certificate from the MTLS connection to the pushed authorization endpoint from the
  *   client application.
  * @param clientCertificatePath
  *   The certificate path presented by the client during client authentication. These certificates
  *   are strings in PEM format.
  * @param dpop
  *   DPoP Header.
  * @param htm
  *   HTTP Method (for DPoP validation).
  * @param htu
  *   HTTP URL base (for DPoP validation).
  */
final case class PushedAuthorizationRequest(
    parameters: String,
    clientId: Option[String] = None,
    clientSecret: Option[String] = None,
    clientCertificate: Option[String] = None,
    clientCertificatePath: Option[String] = None,
    dpop: Option[String] = None,
    htm: Option[String] = None,
    htu: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object PushedAuthorizationRequest {
  // implicit val codec: JsonValueCodec[PushedAuthorizationRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
