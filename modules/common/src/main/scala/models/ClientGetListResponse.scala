package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param developer
  *   The developer of the client applications. If the request did not contain `developer` request
  *   parameter, this property is set to `null`.
  * @param start
  *   Start index (inclusive) of the result set of the query.
  * @param end
  *   End index (exclusive) of the result set of the query.
  * @param totalCount
  *   Total number of clients that belong to the service. This doesn't mean the number of clients
  *   contained in the response.
  * @param clients
  *   An array of clients.
  */
final case class ClientGetListResponse(
    developer: Option[String],
    start: Option[Long],
    end: Option[Long],
    totalCount: Option[Long],
    clients: Option[List[Client]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientGetListResponse {
  // implicit val codec: JsonValueCodec[ClientGetListResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
