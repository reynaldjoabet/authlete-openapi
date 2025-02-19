package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /client/get/list} API.
  * @param start
  *   The start index (inclusive) for the result set of the query.
  * @param end
  *   The end index (exclusive) for the result set of the query.
  * @param developer
  *   The developer of the targeted client applications.
  * @param totalCount
  *   The total count of client applications.
  * @param clients
  *   The client list extracted from the database.
  */

final case class ClientListResponse(
    start: Option[Int],
    `end`: Option[Int],
    developer: Option[String],
    totalCount: Option[Int],
    clients: Option[Array[Client]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
