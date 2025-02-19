package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param ticket
  *   The ticket issued from Authlete `/auth/authorization` API.
  * @param subject
  *   The subject (= a user account managed by the service) who has granted authorization to the
  *   client application.
  * @param authTime
  *   The time when the authentication of the end-user occurred. Its value is the number of seconds
  *   from `1970-01-01`.
  * @param acr
  *   The Authentication Context Class Reference performed for the end-user authentication.
  * @param claims
  *   The claims of the end-user (= pieces of information about the end-user) in JSON format. See
  *   [OpenID Connect Core 1.0, 5.1. Standard
  *   Claims](https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims) for details
  *   about the format.
  * @param properties
  *   Extra properties to associate with an access token and/or an authorization code.
  * @param scopes
  *   Scopes to associate with an access token and/or an authorization code. If a non-empty string
  *   array is given, it replaces the scopes specified by the original authorization request.
  * @param sub
  *   The value of the `sub` claim to embed in an ID token. If this request parameter is `null` or
  *   empty, the value of the `subject` request parameter is used as the value of the `sub` claim.
  * @param idtHeaderParams
  *   JSON that represents additional JWS header parameters for ID tokens that may be issued based
  *   on the authorization request.
  * @param claimsForTx
  *   Claim key-value pairs that are used to compute transformed claims.
  * @param consentedClaims
  *   the claims that the user has consented for the client application to know.
  * @param authorizationDetails
  *   The authorization details. This represents the value of the `authorization_details` request
  *   parameter in the preceding device authorization request which is defined in \"OAuth 2.0 Rich
  *   Authorization Requests\".
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access token.
  * @param accessToken
  *   The representation of an access token that may be issued as a result of the Authlete API call.
  */
final case class AuthorizationIssueRequest(
    ticket: String,
    subject: String,
    authTime: Option[Long] = None,
    acr: Option[String] = None,
    claims: Option[String] = None,
    properties: Option[List[Property]] = None,
    scopes: Option[List[String]] = None,
    sub: Option[String] = None,
    idtHeaderParams: Option[String] = None,
    claimsForTx: Option[String] = None,
    consentedClaims: Option[List[String]] = None,
    authorizationDetails: Option[AuthzDetails] = None,
    jwtAtClaims: Option[String] = None,
    accessToken: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AuthorizationIssueRequest {
  // implicit val codec: JsonValueCodec[AuthorizationIssueRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
