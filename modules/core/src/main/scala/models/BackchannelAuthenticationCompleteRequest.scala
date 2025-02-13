package models

import io.circe.Codec

/** @param ticket
  *   The ticket issued by Authlete\'s `/backchannel/authentication` API.
  * @param result
  *   The result of the end-user authentication and authorization. One of the
  *   following. Details are described in the description.
  * @param subject
  *   The subject (= unique identifier) of the end-user.
  * @param sub
  *   The value of the sub claim that should be used in the ID token.
  * @param authTime
  *   The time at which the end-user was authenticated. Its value is the number
  *   of seconds from `1970-01-01`.
  * @param acr
  *   The reference of the authentication context class which the end-user
  *   authentication satisfied.
  * @param claims
  *   Additional claims which will be embedded in the ID token.
  * @param properties
  *   The extra properties associated with the access token.
  * @param scopes
  *   Scopes to replace the scopes specified in the original backchannel
  *   authentication request with. When nothing is specified for this parameter,
  *   replacement is not performed.
  * @param idtHeaderParams
  *   JSON that represents additional JWS header parameters for ID tokens.
  * @param errorDescription
  *   The description of the error. If this optional request parameter is given,
  *   its value is used as the value of the `error_description` property, but it
  *   is used only when the result is not `AUTHORIZED`. To comply with the
  *   specification strictly, the description must not include characters
  *   outside the set `%x20-21 / %x23-5B / %x5D-7E`.
  * @param errorUri
  *   The URI of a document which describes the error in detail. This
  *   corresponds to the `error_uri` property in the response to the client.
  * @param consentedClaims
  *   the claims that the user has consented for the client application to know.
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access
  *   token.
  * @param accessToken
  *   The representation of an access token that may be issued as a result of
  *   the Authlete API call.
  */
final case class BackchannelAuthenticationCompleteRequest(
    ticket: String,
    result: BackchannelAuthenticationCompleteRequest.BackchannelAuthenticationCompleteRequestResult,
    subject: String,
    sub: Option[String] = None,
    authTime: Option[Long] = None,
    acr: Option[String] = None,
    claims: Option[String] = None,
    properties: Option[List[Property]] = None,
    scopes: Option[List[String]] = None,
    idtHeaderParams: Option[String] = None,
    errorDescription: Option[String] = None,
    errorUri: Option[String] = None,
    consentedClaims: Option[List[String]] = None,
    jwtAtClaims: Option[String] = None,
    accessToken: Option[String] = None
) derives Codec.AsObject

object BackchannelAuthenticationCompleteRequest {
  enum BackchannelAuthenticationCompleteRequestResult(value: String)
      derives Codec.AsObject {
    case TransactionFailed
        extends BackchannelAuthenticationCompleteRequestResult(
          "TRANSACTION_FAILED"
        )
    case AccessDenied
        extends BackchannelAuthenticationCompleteRequestResult("ACCESS_DENIED")
    case Authorized
        extends BackchannelAuthenticationCompleteRequestResult("AUTHORIZED")
  }
}
