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
  *   The HS object created, if applicable.
  */

final case class HskCreateResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[HskCreateResponse.HskCreateResponseAction] = None,
    hsk: Option[Hsk] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskCreateResponse {

  enum HskCreateResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskCreateResponseAction("SUCCESS")
    case InvalidRequest extends HskCreateResponseAction("INVALID_REQUEST")
    case NotFound       extends HskCreateResponseAction("NOT_FOUND")
    case ServerError    extends HskCreateResponseAction("SERVER_ERROR")

  }
  // implicit val codec: JsonValueCodec[HskCreateResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum HskCreateResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskCreateResponseErrorResponse("SUCCESS")
    case InvalidRequest extends HskCreateResponseErrorResponse("INVALID_REQUEST")
    case NotFound       extends HskCreateResponseErrorResponse("NOT_FOUND")
    case ServerError    extends HskCreateResponseErrorResponse("SERVER_ERROR")

  }

}

object HskCreateResponseAction {
//   implicit val codec
//       : JsonValueCodec[HskCreateResponse.HskCreateResponseAction] =
//     JsonCodecMaker.make(codecMakerConfig)
}
