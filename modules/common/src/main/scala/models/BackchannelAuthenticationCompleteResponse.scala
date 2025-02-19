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
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  * @param clientId
  *   The client ID of the client application that has made the backchannel authentication request.
  * @param clientIdAlias
  *   The client ID alias of the client application that has made the backchannel authentication
  *   request.
  * @param clientIdAliasUsed
  *   `true` if the value of the client_id request parameter included in the backchannel
  *   authentication request is the client ID alias. `false` if the value is the original numeric
  *   client ID.
  * @param clientName
  *   The name of the client application which has made the backchannel authentication request.
  * @param deliveryMode
  * @param clientNotificationEndpoint
  *   The client notification endpoint to which a notification needs to be sent. This corresponds to
  *   the `client_notification_endpoint` metadata of the client application.
  * @param clientNotificationToken
  *   The client notification token which needs to be embedded as a Bearer token in the
  *   Authorization header in the notification. This is the value of the `client_notification_token`
  *   request parameter included in the backchannel authentication request.
  * @param authReqId
  *   The newly issued authentication request ID.
  * @param accessToken
  *   The issued access token.
  * @param refreshToken
  *   The issued refresh token.
  * @param idToken
  *   The issued ID token.
  * @param accessTokenDuration
  *   The duration of the access token in seconds.
  * @param refreshTokenDuration
  *   The duration of the refresh token in seconds.
  * @param idTokenDuration
  *   The duration of the ID token in seconds.
  * @param jwtAccessToken
  *   The issued access token in JWT format.
  * @param resources
  *   The resources specified by the `resource` request parameters or by the `resource` property in
  *   the request object. If both are given, the values in the request object should be set. See
  *   \"Resource Indicators for OAuth 2.0\" for details.
  * @param authorizationDetails
  * @param serviceAttributes
  *   The attributes of this service that the client application belongs to.
  * @param clientAttributes
  *   The attributes of the client.
  * @param grantId
  *   the value of the `grant_id` request parameter of the device authorization request. The
  *   `grant_id` request parameter is defined in [Grant Management for OAuth
  *   2.0](https://openid.net/specs/fapi-grant-management.html) , which is supported by Authlete 2.3
  *   and newer versions.
  * @param clientEntityId
  *   The entity ID of the client.
  * @param clientEntityIdUsed
  *   Flag which indicates whether the entity ID of the client was used when the request for the
  *   access token was made.
  */
final case class BackchannelAuthenticationCompleteResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      BackchannelAuthenticationCompleteResponse.BackchannelAuthenticationCompleteResponseAction
    ],
    responseContent: Option[String],
    clientId: Option[Long],
    clientIdAlias: Option[String],
    clientIdAliasUsed: Option[Boolean],
    clientName: Option[String],
    deliveryMode: Option[DeliveryMode],
    clientNotificationEndpoint: Option[String],
    clientNotificationToken: Option[String],
    authReqId: Option[String],
    accessToken: Option[String],
    refreshToken: Option[String],
    idToken: Option[String],
    accessTokenDuration: Option[Long],
    refreshTokenDuration: Option[Long],
    idTokenDuration: Option[Long],
    jwtAccessToken: Option[String],
    resources: Option[List[String]],
    authorizationDetails: Option[AuthzDetails],
    serviceAttributes: Option[List[Pair]],
    clientAttributes: Option[List[Pair]],
    grantId: Option[String],
    clientEntityId: Option[String],
    clientEntityIdUsed: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object BackchannelAuthenticationCompleteResponse {

  enum BackchannelAuthenticationCompleteResponseAction(value: String)
      derives Schema,
        Codec.AsObject {

    case ServerError  extends BackchannelAuthenticationCompleteResponseAction("SERVER_ERROR")
    case NoAction     extends BackchannelAuthenticationCompleteResponseAction("NO_ACTION")
    case Notification extends BackchannelAuthenticationCompleteResponseAction("NOTIFICATION")

  }

  enum BackchannelAuthenticationCompleteResponseErrorResponse(value: String)
      derives Schema,
        Codec.AsObject {

    case ServerError  extends BackchannelAuthenticationCompleteResponseErrorResponse("SERVER_ERROR")
    case NoAction     extends BackchannelAuthenticationCompleteResponseErrorResponse("NO_ACTION")
    case Notification extends BackchannelAuthenticationCompleteResponseErrorResponse("NOTIFICATION")

  }
  // implicit val codec
  //     : JsonValueCodec[BackchannelAuthenticationCompleteResponse] =
  //   JsonCodecMaker.make(codecMakerConfig)

}

object BackchannelAuthenticationCompleteResponseAction {
  // implicit val codec: JsonValueCodec[
  //   BackchannelAuthenticationCompleteResponse.BackchannelAuthenticationCompleteResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
