package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param jose
  *   The JOSE object.
  * @param mandatoryClaims
  *   Mandatory claims that are required to be included in the JOSE object.
  * @param clockSkew
  *   Allowable clock skew in seconds.
  * @param clientIdentifier
  *   The identifier of the client application whose keys are required for verification of the JOSE
  *   object.
  * @param signedByClient
  *   The flag which indicates whether the signature of the JOSE object has been signed by a client
  *   application with the client's private key or a shared symmetric key.
  */
final case class JoseVerifyRequest(
    jose: String,
    mandatoryClaims: Option[String] = None,
    clockSkew: Option[Double] = None,
    clientIdentifier: Option[String] = None,
    signedByClient: Option[Boolean] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object JoseVerifyRequest {
  // implicit val codec: JsonValueCodec[JoseVerifyRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
