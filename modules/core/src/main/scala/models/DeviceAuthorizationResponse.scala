package models

import io.circe._
import io.circe.generic.semiauto._

/** Represents the response from the device authorization endpoint. It includes
  * various details about the authorization process.
  * @param resultCode
  *   The code representing the result of the API call, optional.
  * @param resultMessage
  *   A short message explaining the result of the API call, optional.
  * @param action
  *   The next action that the authorization server implementation should take,
  *   optional.
  * @param responseContent
  *   The content returned to the client application, depending on the value of
  *   `action`, optional.
  * @param clientId
  *   The client ID of the application making the device authorization request,
  *   optional.
  * @param clientIdAlias
  *   The client ID alias, optional.
  * @param clientIdAliasUsed
  *   Indicates whether the `client_id` request parameter was an alias or the
  *   original numeric client ID, optional.
  * @param clientName
  *   The name of the client application making the request, optional.
  * @param clientAuthMethod
  *   The client authentication method, optional.
  * @param scopes
  *   The requested scopes, optional.
  * @param claimNames
  *   The names of claims requested indirectly via special scopes, optional.
  * @param acrs
  *   The list of requested ACR values, optional.
  * @param deviceCode
  *   The device verification code, optional.
  * @param userCode
  *   The end-user verification code, optional.
  * @param verificationUri
  *   The end-user verification URI, optional.
  * @param verificationUriComplete
  *   The verification URI that includes the verification code, optional.
  * @param expiresIn
  *   The duration in seconds that the device verification code is valid for,
  *   optional.
  * @param interval
  *   The minimum amount of time in seconds that the client must wait between
  *   polling requests, optional.
  * @param warnings
  *   Any warnings raised during the processing of the request, optional.
  * @param resources
  *   The resources specified by the `resource` request parameter, optional.
  * @param authorizationDetails
  *   Additional authorization details, optional.
  * @param serviceAttributes
  *   Attributes of the service the client belongs to, optional.
  * @param clientAttributes
  *   Attributes of the client, optional.
  * @param dynamicScopes
  *   The dynamic scopes requested by the client, optional.
  * @param gmAction
  *   The grant management action, optional.
  * @param grantId
  *   The `grant_id` from the device authorization request, optional.
  * @param grant
  *   The grant associated with the request, optional.
  * @param grantSubject
  *   The subject identifying the user who gave the grant, optional.
  * @param clientEntityId
  *   The entity ID of the client, optional.
  * @param clientEntityIdUsed
  *   Indicates whether the entity ID was used in the access token request,
  *   optional.
  */
final case class DeviceAuthorizationResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      DeviceAuthorizationResponse.DeviceAuthorizationResponseAction
    ] = None,
    responseContent: Option[String] = None,
    clientId: Option[Int] = None,
    clientIdAlias: Option[String] = None,
    clientIdAliasUsed: Option[Boolean] = None,
    clientName: Option[String] = None,
    clientAuthMethod: Option[String] = None,
    scopes: Option[List[Scope]] = None,
    claimNames: Option[List[String]] = None,
    acrs: Option[List[String]] = None,
    deviceCode: Option[String] = None,
    userCode: Option[String] = None,
    verificationUri: Option[String] = None,
    verificationUriComplete: Option[String] = None,
    expiresIn: Option[Int] = None,
    interval: Option[Int] = None,
    warnings: Option[List[String]] = None,
    resources: Option[List[String]] = None,
    authorizationDetails: Option[AuthzDetails] = None,
    serviceAttributes: Option[List[Pair]] = None,
    clientAttributes: Option[List[Pair]] = None,
    dynamicScopes: Option[List[DynamicScope]] = None,
    gmAction: Option[GrantManagementAction] = None,
    grantId: Option[String] = None,
    grant: Option[Grant] = None,
    grantSubject: Option[String] = None,
    clientEntityId: Option[String] = None,
    clientEntityIdUsed: Option[Boolean] = None
) //derives Codec.AsObject

object DeviceAuthorizationResponse {
  implicit val codec: Codec[DeviceAuthorizationResponse] = deriveCodec

  enum DeviceAuthorizationResponseAction(val value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends DeviceAuthorizationResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends DeviceAuthorizationResponseAction("BAD_REQUEST")
    case Unauthorized extends DeviceAuthorizationResponseAction("UNAUTHORIZED")
    case Ok extends DeviceAuthorizationResponseAction("OK")
  }
}
