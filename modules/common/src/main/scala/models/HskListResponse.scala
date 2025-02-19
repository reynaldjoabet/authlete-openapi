package common.models

import com.authlete.common.dto.HskListResponse
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /api/hsk/get/list} API.
  *
  * @param resultCode
  * @param resultMessage
  * @param action
  * @param hsks
  */

final case class HskListResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[HskListResponse.HskListResponseAction],
    hsks: Option[Array[Hsk]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskListResponse {

  enum HskListResponseAction(value: String) derives Schema, Codec.AsObject {

    /**
      * The API call succeeded.
      */
    case SUCCESS extends HskListResponseAction("SUCCESS")

    /**
      * The API call was wrong.
      */
    case INVALID_REQUEST extends HskListResponseAction("INVALID_REQUEST")

    /**
      * An error occurred on Authlete side.
      */
    case SERVER_ERROR extends HskListResponseAction("SERVER_ERROR")

  }

}
