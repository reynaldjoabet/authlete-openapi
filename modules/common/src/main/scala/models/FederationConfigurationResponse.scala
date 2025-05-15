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
final case class FederationConfigurationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      FederationConfigurationResponse.FederationConfigurationResponseAction
    ] = None,
    responseContent: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object FederationConfigurationResponse {

  enum FederationConfigurationResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case OK
    case NOT_FOUND
    case INTERNAL_SERVER_ERROR

  }
  // implicit val codec: JsonValueCodec[FederationConfigurationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum FederationConfigurationResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Ok       extends FederationConfigurationResponseErrorResponse("OK")
    case NotFound extends FederationConfigurationResponseErrorResponse("NOT_FOUND")

    case InternalServerError
        extends FederationConfigurationResponseErrorResponse("INTERNAL_SERVER_ERROR")

  }

}
