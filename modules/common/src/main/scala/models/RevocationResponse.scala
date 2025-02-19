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
  *   A short message which explains the result of the API call
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  */
final case class RevocationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[RevocationResponse.RevocationResponseAction],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object RevocationResponse {

  enum RevocationResponseAction(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends RevocationResponseAction("INTERNAL_SERVER_ERROR")
    case InvalidClient       extends RevocationResponseAction("INVALID_CLIENT")
    case BadRequest          extends RevocationResponseAction("BAD_REQUEST")
    case Ok                  extends RevocationResponseAction("OK")

  }

  // implicit val codec: JsonValueCodec[RevocationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum RevocationResponseErrorResponse(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends RevocationResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case InvalidClient       extends RevocationResponseErrorResponse("INVALID_CLIENT")
    case BadRequest          extends RevocationResponseErrorResponse("BAD_REQUEST")
    case Ok                  extends RevocationResponseErrorResponse("OK")

  }

}
