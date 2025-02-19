package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application.
  */
final case class GMResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[GMResponse.GMResponseAction] = None,
    responseContent: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object GMResponse {

  enum GMResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Ok extends GMResponseAction("OK")

    case NoContent extends GMResponseAction("NO_CONTENT")

    case Unauthorized extends GMResponseAction("UNAUTHORIZED")

    case Forbidden extends GMResponseAction("FORBIDDEN")

    case NotFound extends GMResponseAction("NOT_FOUND")

    case CallerError extends GMResponseAction("CALLER_ERROR")

    case AuthleteError extends GMResponseAction("AUTHLETE_ERROR")

  }

  // implicit val codec: JsonValueCodec[GMResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum GMResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Ok extends GMResponseErrorResponse("OK")

    case NoContent extends GMResponseErrorResponse("NO_CONTENT")

    case Unauthorized extends GMResponseErrorResponse("UNAUTHORIZED")

    case Forbidden extends GMResponseErrorResponse("FORBIDDEN")

    case NotFound extends GMResponseErrorResponse("NOT_FOUND")

    case CallerError extends GMResponseErrorResponse("CALLER_ERROR")

    case AuthleteError extends GMResponseErrorResponse("AUTHLETE_ERROR")

  }

}

object GMResponseAction {
  // implicit val codec: JsonValueCodec[GMResponse.GMResponseAction] =
  // JsonCodecMaker.make(codecMakerConfig)
}
