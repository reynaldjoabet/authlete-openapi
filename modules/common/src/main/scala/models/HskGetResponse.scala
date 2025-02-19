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

  enum HskGetResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskGetResponseAction("SUCCESS")
    case InvalidRequest extends HskGetResponseAction("INVALID_REQUEST")
    case NotFound       extends HskGetResponseAction("NOT_FOUND")
    case ServerError    extends HskGetResponseAction("SERVER_ERROR")

  }

  // implicit val codec: JsonValueCodec[HskGetResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum HskGetResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskGetResponseErrorResponse("SUCCESS")
    case InvalidRequest extends HskGetResponseErrorResponse("INVALID_REQUEST")
    case NotFound       extends HskGetResponseErrorResponse("NOT_FOUND")
    case ServerError    extends HskGetResponseErrorResponse("SERVER_ERROR")

  }

}

object HskGetResponseAction {
  // implicit val codec: JsonValueCodec[HskGetResponse.HskGetResponseAction] =
  // JsonCodecMaker.make(codecMakerConfig)
}
