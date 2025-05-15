package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param start
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
    clients: List[Client]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientAuthorizationGetListResponse {
  // implicit val codec: JsonValueCodec[ClientAuthorizationGetListResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
