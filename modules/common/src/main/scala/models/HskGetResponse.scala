package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  * @param action
  *   The result of the API call.
  * @param hsk
  *   The retrieved HS object, if applicable.
  */
final case class HskGetResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[HskGetResponse.HskGetResponseAction] = None,
    hsk: Option[Hsk] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskGetResponse {

  enum HskGetResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SUCCESS
    case INVALID_REQUEST
    case NOT_FOUND
    case SERVER_ERROR

  }

  enum HskGetResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SUCCESS
    case INVALID_REQUEST
    case NOT_FOUND
    case SERVER_ERROR

  }

  // implicit val codec: JsonValueCodec[HskGetResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
