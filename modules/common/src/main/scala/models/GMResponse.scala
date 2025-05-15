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
    responseContent: Option[String] = None,
    dpopNonce: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object GMResponse {

  enum GMResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case OK
    case NO_CONTENT
    case UNAUTHORIZED
    case FORBIDDEN
    case NOT_FOUND
    case CALLER_ERROR
    case AUTHLETE_ERROR

  }

  enum GMResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case UNAUTHORIZED
    case FORBIDDEN
    case NOT_FOUND
    case CALLER_ERROR
    case AUTHLETE_ERROR

  }

  // implicit val codec: JsonValueCodec[GMResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}

object GMResponseAction {
  // implicit val codec: JsonValueCodec[GMResponse.GMResponseAction] =
  // JsonCodecMaker.make(codecMakerConfig)
}
