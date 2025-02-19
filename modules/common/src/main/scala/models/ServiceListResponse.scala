package common.models

import com.authlete.common.dto.ServiceListResponse
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /service/get/list} API.
  *
  * @param start
  *   The start index (inclusive) for the result set of the query.
  * @param end
  *   The end index (exclusive) for the result set of the query.
  * @param totalCount
  *   The total count of services.
  * @param services
  *   The service list extracted from the database.
  */
final case class ServiceListResponse(
    start: Option[Int],
    `end`: Option[Int],
    totalCount: Option[Int],
    services: Option[Array[Service]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
