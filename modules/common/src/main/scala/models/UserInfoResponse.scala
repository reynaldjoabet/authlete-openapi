package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.CodecMakerConfig
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param claims
  *   The list of claims that the client application requests to be embedded in the ID token.
  * @param clientId
  *   The ID of the client application which is associated with the access token.
  * @param clientIdAlias
  *   The client ID alias when the authorization request for the access token was made.
  * @param clientIdAliasUsed
  *   The flag which indicates whether the client ID alias was used when the authorization request
  *   for the access token was made.
  * @param responseContent
  *   The content that the authorization server implementation can use as the value of
  *   `WWW-Authenticate` header on errors.
  * @param scopes
  *   The scopes covered by the access token.
  * @param subject
  *   The subject (= resource owner's ID).
  * @param token
  *   The access token that came along with the userinfo request.
  * @param properties
  *   The extra properties associated with the access token.
  * @param userInfoClaims
  *   The value of the `userinfo` property in the `claims` request parameter or in the `claims`
  *   property in an authorization request object. A client application may request certain claims
  *   be embedded in an ID token or in a response from the userInfo endpoint. There are several
  *   ways. Including the `claims` request parameter and including the `claims` property in a
  *   request object are such examples. In both cases, the value of the `claims` parameter/property
  *   is JSON. Its format is described in [5.5. Requesting Claims using the \"claims\" Request
  *   Parameter](https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter). The
  *   following is an excerpt from the specification. You can find `userinfo` and `id_token` are
  *   top-level properties. ```json { \"userinfo\": { \"given_name\": { \"essential\": true },
  *   \"nickname\": null, \"email\": { \"essential\": true }, \"email_verified\": { \"essential\":
  *   true }, \"picture\": null, \"http://example.info/claims/groups\": null }, \"id_token\": {
  *   \"auth_time\": { \"essential\": true }, \"acr\": { \"values\": [
  *   \"urn:mace:incommon:iap:silver\" ] } } } ```` The value of this property is the value of the
  *   `userinfo` property in JSON format. For example, if the JSON above is included in an
  *   authorization request, this property holds JSON equivalent to the following. ```json {
  *   \"given_name\": { \"essential\": true }, \"nickname\": null, \"email\": { \"essential\": true
  *   }, \"email_verified\": { \"essential\": true }, \"picture\": null,
  *   \"http://example.info/claims/groups\": null } ``` Note that if a request object is given and
  *   it contains the `claims` property and if the `claims` request parameter is also given, the
  *   value of this property holds the former value.
  * @param serviceAttributes
  *   The attributes of this service that the client application belongs to.
  * @param clientAttributes
  *   The attributes of the client.
  * @param consentedClaims
  *   the claims that the user has consented for the client application to know.
  * @param requestedClaimsForTx
  *   Get names of claims that are requested indirectly by <i>\"transformed claims\"</i>. <p> A
  *   client application can request <i>\"transformed claims\"</i> by adding names of transformed
  *   claims in the `claims` request parameter. The following is an example of the `claims` request
  *   parameter that requests a predefined transformed claim named `18_or_over` and a transformed
  *   claim named `nationality_usa` to be embedded in the response from the userinfo endpoint. </p> ```json
  *   { \"transformed_claims\":
  * @param requestedVerifiedClaimsForTx
  *   Names of verified claims that will be referenced when transformed claims are computed.
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class UserInfoResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[UserInfoResponse.UserInfoResponseAction],
    claims: Option[List[String]],
    clientId: Option[Long],
    clientIdAlias: Option[String],
    clientIdAliasUsed: Option[Boolean],
    responseContent: Option[String],
    scopes: Option[List[String]],
    subject: Option[String],
    token: Option[String],
    properties: Option[List[Property]],
    userInfoClaims: Option[String],
    serviceAttributes: Option[List[Pair]],
    clientAttributes: Option[List[Pair]],
    consentedClaims: Option[List[String]],
    requestedClaimsForTx: Option[List[String]],
    requestedVerifiedClaimsForTx: Option[List[List[String]]],
    transformedClaims: Option[String],
    clientEntityId: Option[String],
    clientEntityIdUsed: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object UserInfoResponse {

  enum UserInfoResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends UserInfoResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends UserInfoResponseAction("BAD_REQUEST")
    case Unauthorized        extends UserInfoResponseAction("UNAUTHORIZED")
    case Forbidden           extends UserInfoResponseAction("FORBIDDEN")
    case Ok                  extends UserInfoResponseAction("OK")

  }

  implicit val codec2: JsonValueCodec[Option[UserInfoResponse]] =
    JsonCodecMaker.make(codecMakerConfig)

  enum UserInfoResponseErrorResponse(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends UserInfoResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case BadRequest          extends UserInfoResponseErrorResponse("BAD_REQUEST")
    case Unauthorized        extends UserInfoResponseErrorResponse("UNAUTHORIZED")
    case Forbidden           extends UserInfoResponseErrorResponse("FORBIDDEN")

  }

}
