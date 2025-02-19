package common.models

import com.authlete.common.dto.TokenCreateBatchResponse
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /auth/token/create/batch} API.
  *
  * @param resultCode
  * @param resultMessage
  * @param requestId
  */

final case class TokenCreateBatchResponse(
    resultCode: String,
    resultMessage: String,
    requestId: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
