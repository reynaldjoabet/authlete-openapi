package common.models

import com.authlete.common.dto.TokenListResponse
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /auth/token/get/list} API.
  * @param start
  *   The start index (inclusive) for the result set of the query.
  * @param end
  *   The end index (exclusive) for the result set of the query.
  * @param client
  *   The client associated with the access tokens.
  * @param subject
  *   The identifier of the user associated with the access tokens.
  * @param totalCount
  *   The total count of access tokens.
  * @param accessTokens
  *   The access token list extracted from the database.
  */
final case class TokenListResponse(
    start: Option[Int],
    `end`: Option[Int],
    client: Option[Client],
    subject: Option[String],
    totalCount: Option[Int],
    accessTokens: Option[Array[AccessToken]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
