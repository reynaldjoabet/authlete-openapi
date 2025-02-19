package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /api/hsk/create} API, <code>/api/hsk/delete/{handle}</code> API
  * and <code>/api/hsk/get/{handle}</code> API.
  *
  * @param resultCode
  *   The result of the API call.
  * @param resultMessage
  * @param action
  * @param hsk
  */

final case class HskResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[HskResponse.HskResponseAction],
    hsk: Option[Hsk]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object HskResponse {

  enum HskResponseAction(value: String) derives Schema, Codec.AsObject {

    /**
      * The API call succeeded.
      */
    case SUCCESS extends HskResponseAction("SUCCESS")

    /**
      * The API call was wrong.
      */
    case INVALID_REQUEST extends HskResponseAction("INVALID_REQUEST")

    /**
      * There is no record that corresponds to the specified handle.
      */
    case NOT_FOUND extends HskResponseAction("NOT_FOUND")

    /**
      * An error occurred on Authlete side.
      */
    case SERVER_ERROR extends HskResponseAction("SERVER_ERROR")

  }

}
