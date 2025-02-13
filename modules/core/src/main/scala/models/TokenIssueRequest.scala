package models

import io.circe.Codec

/** @param ticket
  *   The ticket issued from Authlete `/auth/token` API.
  * @param subject
  *   The subject (= unique identifier) of the authenticated user.
  * @param properties
  *   Extra properties to associate with a newly created access token. Note that
  *   properties parameter is accepted only when `Content-Type` of the request
  *   is `application/json`, so don\'t use `application/x-www-form-urlencoded`
  *   if you want to specify properties.
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access
  *   token.
  * @param accessToken
  *   The representation of an access token that may be issued as a result of
  *   the Authlete API call.
  */
final case class TokenIssueRequest(
    ticket: String,
    subject: String,
    properties: Option[List[Property]],
    jwtAtClaims: Option[String],
    accessToken: Option[String]
) derives Codec.AsObject
