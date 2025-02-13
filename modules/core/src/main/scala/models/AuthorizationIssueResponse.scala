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
  *   the client application. Its format varies depending on the value of
  *   `action` parameter.
  * @param accessToken
  *   The newly issued access token. Note that an access token is issued from an
  *   authorization endpoint only when `response_type` contains token.
  * @param accessTokenExpiresAt
  *   The datetime at which the newly issued access token will expire. The value
  *   is represented in milliseconds since the Unix epoch (1970-01-01).
  * @param accessTokenDuration
  *   The duration of the newly issued access token in seconds.
  * @param idToken
  *   The newly issued ID token. Note that an ID token is issued from an
  *   authorization endpoint only when `response_type` contains `id_token`.
  * @param authorizationCode
  *   The newly issued authorization code. Note that an authorization code is
  *   issued only when `response_type` contains code.
  * @param jwtAccessToken
  *   The newly issued access token in JWT format. If the service is not
  *   configured to issue JWT-based access tokens, this property is always set
  *   to `null`.
  */
final case class AuthorizationIssueResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[AuthorizationIssueResponse.AuthorizationIssueResponseAction],
    responseContent: Option[String],
    accessToken: Option[String],
    accessTokenExpiresAt: Option[Long],
    accessTokenDuration: Option[Long],
    idToken: Option[String],
    authorizationCode: Option[String],
    jwtAccessToken: Option[String]
) derives Codec.AsObject

object AuthorizationIssueResponse {

  enum AuthorizationIssueResponseAction(value: String) derives Codec.AsObject {
    case InternalServerError
        extends AuthorizationIssueResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends AuthorizationIssueResponseAction("BAD_REQUEST")
    case Location extends AuthorizationIssueResponseAction("LOCATION")
    case Form extends AuthorizationIssueResponseAction("FORM")
  }
}
