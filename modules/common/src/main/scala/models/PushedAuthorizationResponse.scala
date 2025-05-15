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
  *   The next action that the authorization server should take. Any value other than "CREATED"
  *   should be handled as an unsuccessful result.
  * @param requestUri
  *   The request_uri created for the client to be used in the authorize call.
  * @param responseContent
  *   The content that the authorization server implementation should return to the client
  *   application.
  */
final case class PushedAuthorizationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      PushedAuthorizationResponse.PushedAuthorizationResponseAction
    ] = None,
    requestUri: Option[String] = None,
    responseContent: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object PushedAuthorizationResponse {

  enum PushedAuthorizationResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case CREATED
    case BAD_REQUEST
    case UNAUTHORIZED
    case FORBIDDEN
    case PAYLOAD_TOO_LARGE
    case INTERNAL_SERVER_ERROR

  }

  // implicit val codec: JsonValueCodec[PushedAuthorizationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum PushedAuthorizationResponseErrorResponse
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case CREATED
    case BAD_REQUEST
    case UNAUTHORIZED
    case FORBIDDEN
    case PAYLOAD_TOO_LARGE
    case INTERNAL_SERVER_ERROR

  }

}
