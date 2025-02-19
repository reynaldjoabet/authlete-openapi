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
    hsks: Option[List[Hsk]] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskGetListResponse {

  enum HskGetListResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskGetListResponseAction("SUCCESS")
    case InvalidRequest extends HskGetListResponseAction("INVALID_REQUEST")
    case ServerError    extends HskGetListResponseAction("SERVER_ERROR")

  }
  // implicit val codec: JsonValueCodec[HskGetListResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum HskGetListResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskGetListResponseErrorResponse("SUCCESS")
    case InvalidRequest extends HskGetListResponseErrorResponse("INVALID_REQUEST")
    case ServerError    extends HskGetListResponseErrorResponse("SERVER_ERROR")

  }

}

object HskGetListResponseAction {
  // implicit val codec
  //     : JsonValueCodec[HskGetListResponse.HskGetListResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
