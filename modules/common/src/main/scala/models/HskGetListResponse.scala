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
  * @param hsks
  *   The list of HSK objects.
  */

final case class HskGetListResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[HskGetListResponse.HskGetListResponseAction] = None,
    hsks: List[Hsk] = List.empty
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskGetListResponse {

  enum HskGetListResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SUCCESS
    case INVALID_REQUEST
    case SERVER_ERROR

  }

  // implicit val codec: JsonValueCodec[HskGetListResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum HskGetListResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case SUCCESS
    case INVALID_REQUEST
    case SERVER_ERROR

  }

}

object HskGetListResponseAction {
  // implicit val codec
  //     : JsonValueCodec[HskGetListResponse.HskGetListResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
