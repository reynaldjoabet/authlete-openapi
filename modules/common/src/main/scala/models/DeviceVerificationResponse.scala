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
  * @param clientId
  *   The client ID of the client application to which the user code has been issued.
  * @param clientIdAlias
  *   The client ID alias of the client application to which the user code has been issued.
  * @param clientIdAliasUsed
  *   `true` if the value of the `client_id` request parameter included in the device authorization
  *   request is the client ID alias.
  * @param clientName
  *   The name of the client application to which the user code has been issued.
  * @param scopes
  *   The scopes requested by the device authorization request.
  * @param claimNames
  *   The names of the claims which were requested indirectly via special scopes.
  * @param acrs
  *   The list of ACR values requested by the device authorization request.
  * @param resources
  *   The resources specified by the `resource` request parameters.
  * @param authorizationDetails
  *   The authorization details associated with the device verification response.
  * @param serviceAttributes
  *   The attributes of the service that the client application belongs to.
  * @param clientAttributes
  *   The attributes of the client application.
  * @param dynamicScopes
  *   The dynamic scopes requested by the client application.
  * @param expiresAt
  *   The date in milliseconds since the Unix epoch at which the user code will expire.
  * @param gmAction
  *   The grant management action for the device authorization request.
  * @param grantId
  *   The value of the `grant_id` request parameter of the device authorization request.
  * @param grant
  *   The grant associated with the device verification request.
  * @param grantSubject
  *   The subject identifying the user who has given the grant.
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class DeviceVerificationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      DeviceVerificationResponse.DeviceVerificationResponseAction
    ] = None,
    clientId: Option[Long] = None,
    clientIdAlias: Option[String] = None,
    clientIdAliasUsed: Option[Boolean] = None,
    clientName: Option[String] = None,
    scopes: List[Scope] = List.empty,
    claimNames: List[String] = List.empty,
    acrs: List[String] = List.empty,
    resources: List[String] = List.empty,
    authorizationDetails: Option[AuthzDetails] = None,
    serviceAttributes: List[Pair] = List.empty,
    clientAttributes: List[Pair] = List.empty,
    dynamicScopes: List[DynamicScope] = List.empty,
    expiresAt: Option[Long] = None,
    gmAction: Option[GrantManagementAction] = None,
    grantId: Option[String] = None,
    grant: Option[Grant] = None,
    grantSubject: Option[String] = None,
    clientEntityId: Option[String] = None,
    clientEntityIdUsed: Option[Boolean] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object DeviceVerificationResponse {

  enum DeviceVerificationResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case NOT_EXIST
    case EXPIRED
    case VALID

  }

  enum DeviceVerificationResponseErrorResponse
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case NOT_EXIST
    case EXPIRED
    case VALID

  }

  // implicit val codec: JsonValueCodec[DeviceVerificationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
