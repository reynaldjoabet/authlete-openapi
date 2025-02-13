package models

import io.circe.Codec

/** @param parameters
  *   * OAuth 2.0 authorization request parameters which are the request
  *   parameters that the OAuth 2.0 authorization endpoint of the authorization
  *   server implementation received from the client application. The value of
  *   parameters is either (1) the entire query string when the HTTP method of
  *   the request from the client application is `GET` or (2) the entire entity
  *   body (which is formatted in `application/x-www-form-urlencoded`) when the
  *   HTTP method of the request from the client application is `POST`.
  */
final case class AuthorizationRequest(
    parameters: String
) derives Codec.AsObject

object AuthorizationRequest {
  // given Schema[AuthorizationRequest] =Schema.derived[AuthorizationRequest]
}
