package models

import io.circe.Codec

/** @param start
  *   Start index of search results (inclusive).
  * @param end
  *   End index of search results (exclusive).
  * @param totalCount
  *   Unique ID of a client developer.
  * @param client
  *
  * @param subject
  *   Unique user ID of an end-user.
  * @param accessTokens
  *   An array of access tokens.
  */
final case class TokenGetListResponse(
    start: Option[Int],
    end: Option[Int],
    totalCount: Option[Int],
    client: Option[Client],
    subject: Option[String],
    accessTokens: Option[List[AccessToken]]
) derives Codec.AsObject
