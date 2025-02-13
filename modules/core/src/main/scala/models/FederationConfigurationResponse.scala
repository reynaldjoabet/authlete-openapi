package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to
  *   the client application.
  */
final case class FederationConfigurationResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      FederationConfigurationResponse.FederationConfigurationResponseAction
    ] = None,
    responseContent: Option[String] = None
) derives Codec.AsObject

object FederationConfigurationResponse {
  enum FederationConfigurationResponseAction(val value: String)
      derives Codec.AsObject {
    case Ok extends FederationConfigurationResponseAction("OK")
    case NotFound extends FederationConfigurationResponseAction("NOT_FOUND")
    case InternalServerError
        extends FederationConfigurationResponseAction("INTERNAL_SERVER_ERROR")
  }
}
