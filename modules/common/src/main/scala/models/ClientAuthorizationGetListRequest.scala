package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's <code>/api/client/authorization/get/list</code> API.
  *
  * <p> The API returns a list of client applications to which an end-user has given authorization.
  * </p>
  *
  * <blockquote> <dl> <dt><b><code>subject</code></b></dt> <dd> <p> The subject (= unique
  * identifier) of the end-user who has granted authorization to the client application. </p> </dd>
  *
  * <dt><b><code>start</code></b></dt> <dd> <p> Start index of search results (inclusive). The
  * default value is {@link #DEFAULT_START} (= 0). </p> </dd>
  *
  * <dt><b><code>end</code></b></dt> <dd> <p> End index of search results (exclusive). The default
  * value is {@link #DEFAULT_END} (= 5). </p> </dd>
  *
  * <dt><b><code>developer</code></b></dt> <dd> <p> Unique developer ID. The default value is
  * {@link #DEFAULT_DEVELOPER} (= {@code null}). </p> </dd> </dl> </blockquote>
  * @param subject
  *   Unique identifier of an end-user.
  * @param developer
  *   Unique identifier of a developer. If a non-null value is given, client applications which do
  *   not belong to the developer won't be included in a response from the API.
  * @param start
  *   Start index of search results (inclusive).
  * @param end
  *   End index of search results (exclusive).
  */

final case class ClientAuthorizationGetListRequest(
    subject: String,
    developer: Option[String],
    start: Option[Int],
    `end`: Option[Int]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
