package models

import io.circe.Codec

/** @param token
  *   The access token that has been passed to the userinfo endpoint by the
  *   client application. In other words, the access token which was contained
  *   in the userinfo request.
  * @param claims
  *   Claims in JSON format. As for the format, see [OpenID Connect Core 1.0,
  *   5.1. Standard
  *   Claims](https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims).
  * @param sub
  *   The value of the `sub` claim. If the value of this request parameter is
  *   not empty, it is used as the value of the `sub` claim. Otherwise, the
  *   value of the subject associated with the access token is used.
  * @param claimsForTx
  *   Claim key-value pairs that are used to compute transformed claims.
  * @param requestSignature
  *   The Signature header value from the request.
  * @param headers
  *   HTTP headers to be included in processing the signature. If this is a
  *   signed request, this must include the Signature and Signature-Input
  *   headers, as well as any additional headers covered by the signature.
  */
final case class UserinfoIssueRequest(
    token: String,
    claims: Option[String] = None,
    sub: Option[String] = None,
    claimsForTx: Option[String] = None,
    requestSignature: Option[String] = None,
    headers: Option[Array[Pair]] = None
) derives Codec.AsObject
