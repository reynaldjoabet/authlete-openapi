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
    scopes: Option[List[Scope]] = None,
    claimNames: Option[List[String]] = None,
    acrs: Option[List[String]] = None,
    resources: Option[List[String]] = None,
    authorizationDetails: Option[AuthzDetails] = None,
    serviceAttributes: Option[List[Pair]] = None,
    clientAttributes: Option[List[Pair]] = None,
    dynamicScopes: Option[List[DynamicScope]] = None,
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

  enum DeviceVerificationResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends DeviceVerificationResponseAction("INTERNAL_SERVER_ERROR")
    case NotExist            extends DeviceVerificationResponseAction("NOT_EXIST")
    case Expired             extends DeviceVerificationResponseAction("EXPIRED")
    case Valid               extends DeviceVerificationResponseAction("VALID")

  }
  // implicit val codec: JsonValueCodec[DeviceVerificationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum DeviceVerificationResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError
        extends DeviceVerificationResponseErrorResponse("INTERNAL_SERVER_ERROR")

    case NotExist extends DeviceVerificationResponseErrorResponse("NOT_EXIST")
    case Expired  extends DeviceVerificationResponseErrorResponse("EXPIRED")
    case Valid    extends DeviceVerificationResponseErrorResponse("VALID")

  }

}
