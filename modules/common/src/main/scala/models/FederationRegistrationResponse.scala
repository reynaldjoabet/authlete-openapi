package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Represents the response from the Federation Registration API.
  *
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation can use as the value of the
  *   `WWW-Authenticate` header on errors.
  * @param client
  *   The client associated with this federation registration response.
  */
final case class FederationRegistrationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      FederationRegistrationResponse.FederationRegistrationResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object FederationRegistrationResponse {

  enum FederationRegistrationResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case OK
    case BAD_REQUEST
    case NOT_FOUND
    case INTERNAL_SERVER_ERROR

  }
  // implicit val codec: JsonValueCodec[FederationRegistrationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum FederationRegistrationResponseErrorResponse
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case BAD_REQUEST
    case NOT_FOUND
    case INTERNAL_SERVER_ERROR

  }

}

object FederationRegistrationResponseAction {
  // implicit val codec: JsonValueCodec[
  //   FederationRegistrationResponse.FederationRegistrationResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
