package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe._
import io.circe.generic.semiauto._
import sttp.tapir.Schema

/**
  * Represents the response from the device authorization endpoint. It includes various details
  * about the authorization process.
  * @param resultCode
  *   The code representing the result of the API call, optional.
  * @param resultMessage
  *   A short message explaining the result of the API call, optional.
  * @param action
  *   The next action that the authorization server implementation should take, optional.
  * @param responseContent
  *   The content returned to the client application, depending on the value of `action`, optional.
  * @param clientId
  *   The client ID of the application making the device authorization request, optional.
  * @param clientIdAlias
  *   The client ID alias, optional.
  * @param clientIdAliasUsed
  *   Indicates whether the `client_id` request parameter was an alias or the original numeric
  *   client ID, optional.
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
  *   The duration in seconds that the device verification code is valid for, optional.
  * @param interval
  *   The minimum amount of time in seconds that the client must wait between polling requests,
  *   optional.
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
  *   Indicates whether the entity ID was used in the access token request, optional.
  */
final case class DeviceAuthorizationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      DeviceAuthorizationResponse.DeviceAuthorizationResponseAction
    ] = None,
    responseContent: Option[String] = None,
    clientId: Option[Long] = None,
    clientIdAlias: Option[String] = None,
    clientIdAliasUsed: Option[Boolean] = None,
    clientName: Option[String] = None,
    clientAuthMethod: Option[String] = None,
    scopes: List[Scope] = List.empty,
    claimNames: List[String] = List.empty,
    acrs: List[String] = List.empty,
    deviceCode: Option[String] = None,
    userCode: Option[String] = None,
    verificationUri: Option[String] = None,
    verificationUriComplete: Option[String] = None,
    expiresIn: Option[Int] = None,
    interval: Option[Int] = None,
    warnings: List[String] = List.empty,
    resources: List[String] = List.empty,
    authorizationDetails: Option[AuthzDetails] = None,
    serviceAttributes: List[Pair] = List.empty,
    clientAttributes: List[Pair] = List.empty,
    dynamicScopes: List[DynamicScope] = List.empty,
    gmAction: Option[GrantManagementAction] = None,
    grantId: Option[String] = None,
    grant: Option[Grant] = None,
    grantSubject: Option[String] = None,
    clientEntityId: Option[String] = None,
    clientEntityIdUsed: Option[Boolean] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object DeviceAuthorizationResponse {
  // implicit val codec: Codec[DeviceAuthorizationResponse] = deriveCodec

  enum DeviceAuthorizationResponseAction derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case UNAUTHORIZED
    case OK

  }
  // implicit val jsoniterCodec: JsonValueCodec[DeviceAuthorizationResponse] =
  //   JsonCodecMaker.make(codecMakerConfig)

  enum DeviceAuthorizationResponseErrorResponse derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR

    case BAD_REQUEST
    case UNAUTHORIZED

  }

}

object DeviceAuthorizationResponseAction {

  implicit val codec: JsonValueCodec[
    DeviceAuthorizationResponse.DeviceAuthorizationResponseAction
  ] =
    JsonCodecMaker.make(codecMakerConfig)

}
