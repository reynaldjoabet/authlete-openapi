package models

import io.circe.Codec

/** @param start
  *   Start index of search results (inclusive).
  * @param end
  *   End index of search results (exclusive).
  * @param developer
  *   Unique ID of a client developer.
  * @param subject
  *   Unique user ID of an end-user.
  * @param totalCount
  *   Unique ID of a client developer.
  * @param clients
  *   An array of clients.
  */
final case class ClientAuthorizationGetListResponse(
    start: Option[Long],
    end: Option[Long],
    developer: Option[String],
    subject: Option[String],
    totalCount: Option[Long],
    clients: Option[List[Client]]
) derives Codec.AsObject
