package models

import io.circe.Codec

/** @param ticket
  *   The reason of the failure of the backchannel authentication request. This
  *   request parameter is not mandatory but optional. However, giving this
  *   parameter is recommended. If omitted, `SERVER_ERROR` is used as a reason.
  * @param reason
  *   The reason of the failure of the backchannel authentication request. This
  *   request parameter is not mandatory but optional. However, giving this
  *   parameter is recommended. If omitted, `SERVER_ERROR` is used as a reason.
  * @param errorDescription
  *   The description of the error. This corresponds to the `error_description`
  *   property in the response to the client.
  * @param errorUri
  *   The URI of a document which describes the error in detail. If this
  *   optional request parameter is given, its value is used as the value of the
  *   `error_uri` property.
  */
final case class BackchannelAuthenticationFailRequest(
    ticket: String,
    reason: BackchannelAuthenticationFailRequest.BackchannelAuthenticationFailRequestReason,
    errorDescription: Option[String] = None,
    errorUri: Option[String] = None
) derives Codec.AsObject

object BackchannelAuthenticationFailRequest {

  enum BackchannelAuthenticationFailRequestReason(value: String)
      derives Codec.AsObject {
    case AccessDenied
        extends BackchannelAuthenticationFailRequestReason("ACCESS_DENIED")
    case ExpiredLoginHintToken
        extends BackchannelAuthenticationFailRequestReason(
          "EXPIRED_LOGIN_HINT_TOKEN"
        )
    case InvalidBindingMessage
        extends BackchannelAuthenticationFailRequestReason(
          "INVALID_BINDING_MESSAGE"
        )
    case InvalidTarget
        extends BackchannelAuthenticationFailRequestReason("INVALID_TARGET")
    case InvalidUserCode
        extends BackchannelAuthenticationFailRequestReason("INVALID_USER_CODE")
    case MissingUserCode
        extends BackchannelAuthenticationFailRequestReason("MISSING_USER_CODE")
    case ServerError
        extends BackchannelAuthenticationFailRequestReason("SERVER_ERROR")
    case UnauthorizedClient
        extends BackchannelAuthenticationFailRequestReason(
          "UNAUTHORIZED_CLIENT"
        )
    case UnknownUserId
        extends BackchannelAuthenticationFailRequestReason("UNKNOWN_USER_ID")
  }
}
