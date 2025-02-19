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
  *   The code which represents the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  */
final case class TokenFailResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[TokenFailResponse.TokenFailResponseAction],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenFailResponse {

  enum TokenFailResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends TokenFailResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends TokenFailResponseAction("BAD_REQUEST")

  }

  // implicit val codec: JsonValueCodec[TokenFailResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum TokenFailResponseErrorResponse(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends TokenFailResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case BadRequest          extends TokenFailResponseErrorResponse("BAD_REQUEST")

  }

}
