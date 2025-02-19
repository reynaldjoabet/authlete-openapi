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

  enum HskDeleteResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskDeleteResponseAction("SUCCESS")
    case InvalidRequest extends HskDeleteResponseAction("INVALID_REQUEST")
    case NotFound       extends HskDeleteResponseAction("NOT_FOUND")
    case ServerError    extends HskDeleteResponseAction("SERVER_ERROR")

  }

  // implicit val codec: JsonValueCodec[HskDeleteResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum HskDeleteResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Success        extends HskDeleteResponseErrorResponse("SUCCESS")
    case InvalidRequest extends HskDeleteResponseErrorResponse("INVALID_REQUEST")
    case NotFound       extends HskDeleteResponseErrorResponse("NOT_FOUND")
    case ServerError    extends HskDeleteResponseErrorResponse("SERVER_ERROR")

  }

}

object HskDeleteResponseAction {
  // implicit val codec
  //     : JsonValueCodec[HskDeleteResponse.HskDeleteResponseAction] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
