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
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param client
  * @param display
  * @param maxAge
  *   The maximum authentication age. This value comes from `max_age` request parameter, or
  *   `defaultMaxAge` configuration parameter of the client application when the authorization
  *   request does not contain `max_age` request parameter. See \"[OpenID Connect Core 1.0,
  * @param service
  * @param scopes
  *   The scopes that the client application requests. This value comes from `scope` request
  *   parameter. If the request does not contain `scope` parameter, this parameter is a list of
  *   scopes which are registered as default. If the authorization request does not have `scope`
  *   request parameter and the service has not registered any default scope, the value of this
  *   parameter is `null`. It is ensured that scopes listed by this parameters are contained in the
  *   list of supported scopes which are specified by `supportedScopes` configuration parameter of
  *   the service. Unsupported scopes in the authorization request do not cause an error and are
  *   just ignored. OpenID Connect defines some scope names which need to be treated specially. The
  *   table below lists the special scope names. | Name | Description | | --- | --- | | `openid` |
  *   This scope must be contained in `scope` request parameter to promote an OAuth 2.0
  *   authorization request to an OpenID Connect request. It is described in \"[OpenID Connect Core
  *   1.0, 3.1.2.1. Authentication
  *   Request](https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest), scope\". | |
  *   `profile` | This scope is used to request some claims to be embedded in the ID token. The
  *   claims are `name`, `family_name`, `given_name`, `middle_name`, `nickname`,
  *   `preferred_username`, `profile`, `picture`, `website`, `gender`, `birthdate`, `zoneinfo`,
  *   `locale`, and `updated_at`. It is described in [OpenID Connect Core 1.0, 5.4. Requesting
  *   Claims using Scope Values](https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims).
  *   \| | `email` | This scope is used to request some claims to be embedded in the ID token. The
  *   claims are `email` and `email_verified`. It is described in [OpenID Connect Core 1.0, 5.4.
  *   Requesting Claims using Scope
  *   Values](https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims). \| | `address` |
  *   This scope is used to request `address` claim to be embedded in the ID token. It is described
  *   in [OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope
  *   Values](https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims).<br><br> The format
  *   of `address` claim is not a simple string. It is described in [OpenID Connect Core 1.0, 5.1.1.
  *   Address Claim](https://openid.net/specs/openid-connect-core-1_0.html#AddressClaim). \| |
  *   `phone` | This scope is used to request some claims to be embedded in the ID token. The claims
  *   are `phone_number` and `phone_number_verified`. It is described in [OpenID Connect Core 1.0,
  *   5.4. Requesting Claims using Scope
  *   Values](https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims). \| |
  *   `offline_access` | The following is an excerpt about this scope from [OpenID Connect Core 1.0,
  *   11. Offline Access](https://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess).
  *       <blockquote>This scope value requests that an OAuth 2.0 Refresh Token be issued that can
  *       be used to obtain an Access Token that grants access to the end-user\'s userinfo endpoint
  *       even when the end-user is not present (not logged in).</blockquote> | Note that, if
  *       `response_type` request parameter does not contain code, `offline_acccess` scope is
  *       removed from this list even when scope request parameter contains `offline_access`. This
  *       behavior is a requirement written in [OpenID Connect Core 1.0, 11. Offline
  *       Access](https://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess).
  * @param uiLocales
  *   The locales that the client application presented as candidates to be used for UI. This value
  *   comes from `ui_locales` request parameter. The format of `ui_locales` is a space-separated
  *   list of language tag values defined in
  *   [RFC5646](https://datatracker.ietf.org/doc/html/rfc5646). See \"[OpenID Connect Core 1.0,
  * @param claimsLocales
  *   End-user\'s preferred languages and scripts for claims. This value comes from `claims_locales`
  *   request parameter. The format of `claims_locales` is a space-separated list of language tag
  *   values defined in [RFC5646](https://datatracker.ietf.org/doc/html/rfc5646). See \"[OpenID
  *   Connect Core 1.0,
  * @param claims
  *   The list of claims that the client application requests to be embedded in the ID token. The
  *   value comes from (1) `id_token` in `claims` request parameter [1] and/or (2) special scopes
  *   (`profile`, `email`, `address` and `phone`) which are expanded to claims. See [OpenID Connect
  *   Core 1.0,
  * @param acrEssential
  *   This boolean value indicates whether the authentication of the end-user must be one of the
  *   ACRs (Authentication Context Class References) listed in `acrs` parameter. This parameter
  *   becomes `true` only when (1) the authorization request contains `claims` request parameter and
  *   (2) `acr` claim is in it, and (3) `essential` property of the `acr` claim is `true`. See
  *   [OpenID Connect Core 1.0,
  * @param clientIdAliasUsed
  *   `true` if the value of the `client_id` request parameter included in the authorization request
  *   is the client ID alias. `false` if the value is the original numeric client ID.
  * @param acrs
  *   The list of ACRs (Authentication Context Class References) one of which the client application
  *   requests to be satisfied for the authentication of the end-user. This value comes from
  *   `acr_values` request parameter or `defaultAcrs` configuration parameter of the client
  *   application. See \"[OpenID Connect Core 1.0,
  * @param subject
  *   The subject (= unique user ID managed by the authorization server implementation) that the
  *   client application expects to grant authorization. The value comes from `sub` claim in
  *   `claims` request parameter.
  * @param loginHint
  *   A hint about the login identifier of the end-user. The value comes from `login_hint` request
  *   parameter.
  * @param prompts
  *   The list of values of prompt request parameter. See \"[OpenID Connect Core 1.0,
  * @param lowestPrompt
  * @param requestObjectPayload
  *   The payload part of the request object. The value of this proprty is `null` if the
  *   authorization request does not include a request object.
  * @param idTokenClaims
  *   The value of the `id_token` property in the claims request parameter or in the claims property
  *   in a request object. A client application may request certain claims be embedded in an ID
  *   token or in a response from the userInfo endpoint. There are several ways. Including the
  *   `claims` request parameter and including the `claims` property in a request object are such
  *   examples. In both the cases, the value of the `claims` parameter/property is JSON. Its format
  *   is described in [5.5. Requesting Claims using the \"claims\" Request
  *   Parameter](https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter). The
  *   following is an excerpt from the specification. You can find `userinfo` and `id_token` are
  *   top-level properties. ```json { \"userinfo\": { \"given_name\": { \"essential\": true },
  *   \"nickname\": null, \"email\": { \"essential\": true }, \"email_verified\": { \"essential\":
  *   true }, \"picture\": null, \"http://example.info/claims/groups\": null }, \"id_token\": {
  *   \"auth_time\": { \"essential\": true }, \"acr\": { \"values\": [
  *   \"urn:mace:incommon:iap:silver\" ] } } } ``` This value of this property is the value of the
  *   `id_token` property in JSON format. For example, if the JSON above is included in an
  *   authorization request, this property holds JSON equivalent to the following. ```json {
  *   \"auth_time\": { \"essential\": true }, \"acr\": { \"values\": [
  *   \"urn:mace:incommon:iap:silver\" ] } } ``` Note that if a request object is given and it
  *   contains the `claims` property and if the `claims` request parameter is also given, this
  *   property holds the former value.
  * @param userInfoClaims
  *   The value of the `userinfo` property in the `claims` request parameter or in the `claims`
  *   property in a request object. A client application may request certain claims be embedded in
  *   an ID token or in a response from the userInfo endpoint. There are several ways. Including the
  *   `claims` request parameter and including the `claims` property in a request object are such
  *   examples. In both the cases, the value of the `claims` parameter/property is JSON. Its format
  *   is described in [5.5. Requesting Claims using the \"claims\" Request
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
  * @param resources
  *   The resources specified by the `resource` request parameters or by the `resource` property in
  *   the request object. If both are given, the values in the request object should be set. See
  *   \"Resource Indicators for OAuth 2.0\" for details.
  * @param authorizationDetails
  * @param purpose
  *   The `purpose` request parameter is defined in [9. Transaction-specific
  *   Purpose](https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html#name-transaction-specific-purpos)
  *   of [OpenID Connect for Identity Assurance
  *   1.0](https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html) as follows: >
  *   purpose: OPTIONAL. String describing the purpose for obtaining certain user data from the OP.
  *   The purpose MUST NOT be shorter than 3 characters and MUST NOT be longer than 300 characters.
  *   If these rules are violated, the authentication request MUST fail and the OP returns an error
  *   invalid_request to the RP.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  * @param ticket
  *   A ticket issued by Authlete to the service implementation. This is needed when the service
  *   implementation calls either `/auth/authorization/fail` API or `/auth/authorization/issue` API.
  * @param dynamicScopes
  *   The dynamic scopes which the client application requested by the scope request parameter.
  * @param gmAction
  * @param grantId
  *   the value of the `grant_id` request parameter of the device authorization request. The
  *   `grant_id` request parameter is defined in [Grant Management for OAuth
  *   2.0](https://openid.net/specs/fapi-grant-management.html) , which is supported by Authlete 2.3
  *   and newer versions.
  * @param grant
  * @param grantSubject
  *   The subject identifying the user who has given the grant identified by the `grant_id` request
  *   parameter of the device authorization request. Authlete 2.3 and newer versions support <a
  *   href= \"https://openid.net/specs/fapi-grant-management.html\">Grant Management for OAuth
  *   2.0</a>. An authorization request may contain a `grant_id` request parameter which is defined
  *   in the specification. If the value of the request parameter is valid,
  *   {@@@@@@@@@@@@@@@@@@@@@@link#getGrantSubject()} will return the subject of the user who has
  *   given the grant to the client application. Authorization server implementations may use the
  *   value returned from {@@@@@@@@@@@@@@@@@@@@@@link#getGrantSubject()} in order to determine the
  *   user to authenticate. The user your system will authenticate during the authorization process
  *   (or has already authenticated) may be different from the user of the grant. The first
  *   implementer\'s draft of \"Grant Management for OAuth 2.0\" does not mention anything about the
  *   case, so the behavior in the case is left to implementations. Authlete will not perform the
  *   grant management action when the `subject` passed to Authlete does not match the user of the
  *   grant.
  * @param requestedClaimsForTx
  *   Get names of claims that are requested indirectly by <i>\"transformed claims\"</i>. <p> A
  *   client application can request <i>\"transformed claims\"</i> by adding names of transformed
  *   claims in the `claims` request parameter. The following is an example of the `claims` request
  *   parameter that requests a predefined transformed claim named `18_or_over` and a transformed
  *   claim named `nationality_usa` to be embedded in the response from the userinfo endpoint. </p> ```json
  *   { \"transformed_claims\":
  * @param requestedVerifiedClaimsForTx
  *   Names of verified claims that will be referenced when transformed claims are computed.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */

final case class AuthorizationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[AuthorizationResponse.AuthorizationResponseAction],
    client: Option[Client],
    display: Option[Display],
    maxAge: Option[Long],
    service: Option[Service],
    scopes: Option[List[Scope]],
    uiLocales: Option[List[String]],
    claimsLocales: Option[List[String]],
    claims: Option[List[String]],
    acrEssential: Option[Boolean],
    clientIdAliasUsed: Option[Boolean],
    acrs: Option[List[String]],
    subject: Option[String],
    loginHint: Option[String],
    prompts: Option[List[Prompt]],
    lowestPrompt: Option[Prompt],
    requestObjectPayload: Option[String],
    idTokenClaims: Option[String],
    userInfoClaims: Option[String],
    resources: Option[List[String]],
    authorizationDetails: Option[AuthzDetails],
    purpose: Option[String],
    responseContent: Option[String],
    ticket: Option[String],
    dynamicScopes: Option[List[DynamicScope]],
    gmAction: Option[GrantManagementAction],
    grantId: Option[String],
    grant: Option[Grant],
    grantSubject: Option[String],
    requestedClaimsForTx: Option[List[String]],
    requestedVerifiedClaimsForTx: Option[List[List[String]]],
    transformedClaims: Option[String],
    clientEntityIdUsed: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AuthorizationResponse {

  enum AuthorizationResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends AuthorizationResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends AuthorizationResponseAction("BAD_REQUEST")
    case Location            extends AuthorizationResponseAction("LOCATION")
    case Form                extends AuthorizationResponseAction("FORM")
    case NoInteraction       extends AuthorizationResponseAction("NO_INTERACTION")
    case Interaction         extends AuthorizationResponseAction("INTERACTION")

    override def toString(): String = value

  }

  enum AuthorizationResponseErrorResponse(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends AuthorizationResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case BadRequest          extends AuthorizationResponseErrorResponse("BAD_REQUEST")
    case Location            extends AuthorizationResponseErrorResponse("LOCATION")
    case Form                extends AuthorizationResponseErrorResponse("FORM")
    case NoInteraction       extends AuthorizationResponseErrorResponse("NO_INTERACTION")
    case Interaction         extends AuthorizationResponseErrorResponse("INTERACTION")

    override def toString(): String = value

  }

  // implicit val codec: JsonValueCodec[AuthorizationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

}

object AuthorizationResponseAction {
  // implicit val codec
  //     : JsonValueCodec[AuthorizationResponse.AuthorizationResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
