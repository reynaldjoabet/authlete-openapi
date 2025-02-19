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

  enum PushedAuthorizationResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Created             extends PushedAuthorizationResponseAction("CREATED")
    case BadRequest          extends PushedAuthorizationResponseAction("BAD_REQUEST")
    case Unauthorized        extends PushedAuthorizationResponseAction("UNAUTHORIZED")
    case Forbidden           extends PushedAuthorizationResponseAction("FORBIDDEN")
    case PayloadTooLarge     extends PushedAuthorizationResponseAction("PAYLOAD_TOO_LARGE")
    case InternalServerError extends PushedAuthorizationResponseAction("INTERNAL_SERVER_ERROR")

  }

  // implicit val codec: JsonValueCodec[PushedAuthorizationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum PushedAuthorizationResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Created         extends PushedAuthorizationResponseErrorResponse("CREATED")
    case BadRequest      extends PushedAuthorizationResponseErrorResponse("BAD_REQUEST")
    case Unauthorized    extends PushedAuthorizationResponseErrorResponse("UNAUTHORIZED")
    case Forbidden       extends PushedAuthorizationResponseErrorResponse("FORBIDDEN")
    case PayloadTooLarge extends PushedAuthorizationResponseErrorResponse("PAYLOAD_TOO_LARGE")

    case InternalServerError
        extends PushedAuthorizationResponseErrorResponse("INTERNAL_SERVER_ERROR")

  }

}
