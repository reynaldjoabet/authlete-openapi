package models

import io.circe.Codec

/** @param resultCode
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
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    valid: Option[Boolean] = None,
    signatureValid: Option[Boolean] = None,
    missingClaims: Option[List[String]] = None,
    invalidClaims: Option[List[String]] = None,
    errorDescriptions: Option[List[String]] = None
) derives Codec.AsObject
