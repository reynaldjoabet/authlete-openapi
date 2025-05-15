package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param start
  *   Start index (inclusive) of the result set. The default value is 0. Must not be a negative
  *   number.
  * @param end
  *
  * @param totalCount
  *   Total number of services owned by the service owner. This doesn't mean the number of services
  *   contained in the response.
  * @param services
  *   An array of services.
  */
final case class ServiceGetListResponse(
    start: Option[Int],
    end: Option[Int],
    totalCount: Option[Int],
    services: List[Service]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ServiceGetListResponse {
  // implicit val codec: JsonValueCodec[ServiceGetListResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
