package models

import io.circe.*
import io.circe.generic.semiauto.*

/** @param userCode
  *   A user code used for device verification.
  * @param result
  *   The result of the end-user authentication and authorization.
  * @param subject
  *   The subject (unique identifier) of the end-user.
  * @param sub
  *   The value of the sub claim to be used in the ID token, optional.
  * @param authTime
  *   The time the end-user was authenticated, in seconds since the Unix epoch,
  *   optional.
  * @param acr
  *   The authentication context reference, optional.
  * @param claims
  *   Additional claims to be embedded in the ID token, optional.
  * @param properties
  *   Extra properties associated with the access token, optional.
  * @param scopes
  *   Scopes to replace the ones specified in the original device authorization
  *   request, optional.
  * @param errorDescription
  *   A description of the error, used when the result is not `AUTHORIZED`,
  *   optional.
  * @param errorUri
  *   A URI pointing to a document that describes the error in detail, optional.
  * @param idtHeaderParams
  *   JSON representing additional JWS header parameters for ID tokens,
  *   optional.
  * @param consentedClaims
  *   Claims the user has consented for the client application to know,
  *   optional.
  * @param jwtAtClaims
  *   Additional claims added to the JWT access token payload, optional.
  */
final case class DeviceCompleteRequest(
    userCode: String,
    result: DeviceCompleteRequest.DeviceCompleteRequestResult,
    subject: String,
    sub: Option[String] = None,
    authTime: Option[Long] = None,
    acr: Option[String] = None,
    claims: Option[String] = None,
    properties: Option[List[Property]] = None,
    scopes: Option[List[String]] = None,
    errorDescription: Option[String] = None,
    errorUri: Option[String] = None,
    idtHeaderParams: Option[String] = None,
    consentedClaims: Option[List[String]] = None,
    jwtAtClaims: Option[String] = None
)

object DeviceCompleteRequest {
  implicit val codec: Codec[DeviceCompleteRequest] = deriveCodec

  enum DeviceCompleteRequestResult(val value: String) derives Codec.AsObject {
    case TransactionFailed
        extends DeviceCompleteRequestResult("TRANSACTION_FAILED")
    case AccessDenied extends DeviceCompleteRequestResult("ACCESS_DENIED")
    case Authorized extends DeviceCompleteRequestResult("AUTHORIZED")
  }
}
