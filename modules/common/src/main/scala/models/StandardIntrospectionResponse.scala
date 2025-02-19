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
final case class StandardIntrospectionResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      StandardIntrospectionResponse.StandardIntrospectionResponseAction
    ],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object StandardIntrospectionResponse {

  enum StandardIntrospectionResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends StandardIntrospectionResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends StandardIntrospectionResponseAction("BAD_REQUEST")
    case Ok                  extends StandardIntrospectionResponseAction("OK")

  }

  enum StandardIntrospectionResponseErrorResponse(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError
        extends StandardIntrospectionResponseErrorResponse("INTERNAL_SERVER_ERROR")

    case BadRequest extends StandardIntrospectionResponseErrorResponse("BAD_REQUEST")
    case Ok         extends StandardIntrospectionResponseErrorResponse("OK")

  }

}
