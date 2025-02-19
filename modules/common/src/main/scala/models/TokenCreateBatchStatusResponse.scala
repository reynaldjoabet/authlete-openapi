package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /auth/token/create/batch/status} API.
  *
  * @param resultCode
  * @param resultMessage
  * @param status
  */
final case class TokenCreateBatchStatusResponse(
    resultCode: String,
    resultMessage: String,
    status: Option[TokenBatchStatus]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
