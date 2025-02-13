package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation can use as the
  *   value of `WWW-Authenticate` header on errors.
  * @param clientId
  *   The client ID.
  * @param clientIdAlias
  *   The client ID alias when the token request was made.
  * @param clientIdAliasUsed
  *   The flag which indicates whether the client ID alias was used when the
  *   token request was made.
  * @param expiresAt
  *   The time at which the access token expires (milliseconds since Unix
  *   epoch).
  * @param subject
  *   The subject who is associated with the access token.
  * @param scopes
  *   The scopes covered by the access token.
  * @param existent
  *   `true` if the access token exists.
  * @param usable
  *   `true` if the access token is usable (= exists and has not expired).
  * @param sufficient
  *   `true` if the access token exists.
  * @param refreshable
  *   `true` if the access token can be refreshed using the associated refresh
  *   token.
  * @param properties
  *   The extra properties associated with the access token.
  * @param certificateThumbprint
  *   The client certificate thumbprint used to validate the access token.
  * @param resources
  *   The target resources specified by the `resource` request parameters.
  * @param accessTokenResources
  *   The target resources after narrowing down by subsequent requests.
  * @param authorizationDetails
  *   Authorization details related to the introspection response.
  * @param serviceAttributes
  *   The attributes of the service that the client application belongs to.
  * @param clientAttributes
  *   The attributes of the client.
  * @param scopeDetails
  *   The scope details, including scope attributes.
  * @param grantId
  *   The value of the `grant_id` request parameter from the device
  *   authorization request.
  * @param grant
  *   The grant associated with the access token.
  * @param forExternalAttachment
  *   The flag which indicates whether the access token is for an external
  *   attachment.
  * @param consentedClaims
  *   The claims that the user has consented for the client application to know.
  * @param grantType
  *   The grant type associated with the access token.
  * @param acr
  *   The Authentication Context Class Reference of the user authentication
  *   during access token issuance.
  * @param authTime
  *   The time when the user authentication was performed during the course of
  *   issuing the access token.
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the
  *   request for the access token was made.
  */
final case class IntrospectionResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[IntrospectionResponse.IntrospectionResponseAction] = None,
    responseContent: Option[String] = None,
    clientId: Option[Int] = None,
    clientIdAlias: Option[String] = None,
    clientIdAliasUsed: Option[Boolean] = None,
    expiresAt: Option[Long] = None,
    subject: Option[String] = None,
    scopes: Option[Seq[String]] = None,
    existent: Option[Boolean] = None,
    usable: Option[Boolean] = None,
    sufficient: Option[Boolean] = None,
    refreshable: Option[Boolean] = None,
    properties: Option[Seq[Property]] = None,
    certificateThumbprint: Option[String] = None,
    resources: Option[Seq[String]] = None,
    accessTokenResources: Option[Seq[String]] = None,
    authorizationDetails: Option[AuthzDetails] = None,
    serviceAttributes: Option[Seq[Pair]] = None,
    clientAttributes: Option[Seq[Pair]] = None,
    scopeDetails: Option[Seq[Scope]] = None,
    grantId: Option[String] = None,
    grant: Option[Grant] = None,
    forExternalAttachment: Option[Boolean] = None,
    consentedClaims: Option[Seq[String]] = None,
    grantType: Option[GrantType] = None,
    acr: Option[String] = None,
    authTime: Option[Long] = None,
    clientEntityId: Option[String] = None,
    clientEntityIdUsed: Option[Boolean] = None
) derives Codec.AsObject

object IntrospectionResponse {
  enum IntrospectionResponseAction(val action: String) derives Codec.AsObject {
    case InternalServerError
        extends IntrospectionResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends IntrospectionResponseAction("BAD_REQUEST")
    case Unauthorized extends IntrospectionResponseAction("UNAUTHORIZED")
    case Forbidden extends IntrospectionResponseAction("FORBIDDEN")
    case Ok extends IntrospectionResponseAction("OK")
  }
}
