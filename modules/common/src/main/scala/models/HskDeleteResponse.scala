package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._
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
  *   The HS object deleted, if applicable.
  */

final case class HskDeleteResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[HskDeleteResponse.HskDeleteResponseAction] = None,
    hsk: Option[Hsk] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskDeleteResponse {

  enum HskDeleteResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SUCCESS
    case INVALID_REQUEST
    case NOT_FOUND
    case SERVER_ERROR

  }

  // implicit val codec: JsonValueCodec[HskDeleteResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum HskDeleteResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SUCCESS
    case INVALID_REQUEST
    case NOT_FOUND
    case SERVER_ERROR

  }

}

object HskDeleteResponseAction {
  // implicit val codec
  //     : JsonValueCodec[HskDeleteResponse.HskDeleteResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
