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

  enum FederationRegistrationResponseAction(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Ok                  extends FederationRegistrationResponseAction("OK")
    case BadRequest          extends FederationRegistrationResponseAction("BAD_REQUEST")
    case NotFound            extends FederationRegistrationResponseAction("NOT_FOUND")
    case InternalServerError extends FederationRegistrationResponseAction("INTERNAL_SERVER_ERROR")

  }
  // implicit val codec: JsonValueCodec[FederationRegistrationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum FederationRegistrationResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case Ok         extends FederationRegistrationResponseErrorResponse("OK")
    case BadRequest extends FederationRegistrationResponseErrorResponse("BAD_REQUEST")
    case NotFound   extends FederationRegistrationResponseErrorResponse("NOT_FOUND")

    case InternalServerError
        extends FederationRegistrationResponseErrorResponse("INTERNAL_SERVER_ERROR")

  }

}

object FederationRegistrationResponseAction {
  // implicit val codec: JsonValueCodec[
  //   FederationRegistrationResponse.FederationRegistrationResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
