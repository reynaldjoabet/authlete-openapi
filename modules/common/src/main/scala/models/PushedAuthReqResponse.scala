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
  *   The next action that the authorization server implementation should take. Any other value
  *   other than `CREATED` should be handled as unsuccessful result.
  * @param requestUri
  *   The request_uri created for the client to be used in the authorize call.
  * @param responseContent
  *   The content that the authorization server implementation should return to the client
  *   application.
  */
final case class PushedAuthReqResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[PushedAuthReqResponse.PushedAuthReqResponseAction] = None,
    requestUri: Option[String] = None,
    responseContent: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object PushedAuthReqResponse {

  enum PushedAuthReqResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case CREATED
    case BAD_REQUEST
    case UNAUTHORIZED
    case FORBIDDEN
    case PAYLOAD_TOO_LARGE
    case INTERNAL_SERVER_ERROR

  }

  enum PushedAuthReqResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case CREATED
    case BAD_REQUEST
    case UNAUTHORIZED
    case FORBIDDEN
    case PAYLOAD_TOO_LARGE
    case INTERNAL_SERVER_ERROR

  }

  // implicit val codec: JsonValueCodec[PushedAuthReqResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}
