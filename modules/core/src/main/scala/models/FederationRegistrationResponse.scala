package models

import io.circe.Codec

/** Represents the response from the Federation Registration API.
  *
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation can use as the
  *   value of the `WWW-Authenticate` header on errors.
  * @param client
  *   The client associated with this federation registration response.
  */
final case class FederationRegistrationResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    action: Option[
      FederationRegistrationResponse.FederationRegistrationResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives Codec.AsObject

object FederationRegistrationResponse {
  enum FederationRegistrationResponseAction(val value: String)
      derives Codec.AsObject {
    case Ok extends FederationRegistrationResponseAction("OK")
    case BadRequest extends FederationRegistrationResponseAction("BAD_REQUEST")
    case NotFound extends FederationRegistrationResponseAction("NOT_FOUND")
    case InternalServerError
        extends FederationRegistrationResponseAction("INTERNAL_SERVER_ERROR")
  }
}
