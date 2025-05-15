package requests

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import common.models.*
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param parameters
  *   The request parameters of the PAR request.
  * @param authorization
  *   * Get the value of the {@code Authorization} header in the PAR request. A pair of client ID
  *   and client secret is embedded there when the client authentication method is
  *   {@code client_secret_basic}.
  *
  * @param clientCertificatePath
  *   Get the path of the client's certificate, each in PEM format. The first item in the array is
  *   the client's certificate itself.
  *
  * @return
  *   The path of the client's certificate.
  *
  * @see
  *   <a href="https://www.rfc-editor.org/rfc/rfc8705.html" >RFC 8705: OAuth 2.0 Mutual-TLS Client
  *   Authentication and Certificate-Bound Access Tokens</a>
  *
  * @param dpop
  *   * Get the DPoP proof JWT (the value of the {@code DPoP} HTTP header).
  *
  * @return
  *   The DPoP proof JWT.
  *
  * @see
  *   <a href="https://www.rfc-editor.org/rfc/rfc9449.html" >RFC 9449: OAuth 2.0 Demonstrating Proof
  *   of Possession (DPoP)</a>
  */
final case class PushedAuthReqParams(
    parameters: Map[String, String],
    authorization: String,
    clientCertificatePath: List[String],
    dpop: String,
    htm: String,
    htu: String,
    clientAttestation: String,
    clientAttestationPop: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
