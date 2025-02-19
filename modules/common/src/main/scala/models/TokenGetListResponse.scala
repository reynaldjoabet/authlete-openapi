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
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenGetListResponse {
  // implicit val codec: JsonValueCodec[TokenGetListResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
