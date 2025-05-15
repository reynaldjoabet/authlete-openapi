package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param valid
  *   The result of the verification on the JOSE object.
  * @param signatureValid
  *   The result of the signature verification.
  * @param missingClaims
  *   The list of missing claims.
  * @param invalidClaims
  *   The list of invalid claims.
  * @param errorDescriptions
  *   The list of error messages.
  */
final case class JoseVerifyResponse(
    resultCode: String,
    resultMessage: String,
    valid: Option[Boolean] = None,
    signatureValid: Option[Boolean] = None,
    missingClaims: List[String] = List.empty,
    invalidClaims: List[String] = List.empty,
    errorDescriptions: List[String] = List.empty
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object JoseVerifyResponse {
  // implicit val codec: JsonValueCodec[JoseVerifyResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
  given JsonValueCodec[JoseVerifyResponse] = JsonCodecMaker.make(codecMakerConfig)
}
