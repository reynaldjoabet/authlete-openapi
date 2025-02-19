package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.generic.semiauto._
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
  * @param client
  *   The client associated with the registration.
  */
final case class ClientRegistrationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      ClientRegistrationResponse.ClientRegistrationResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationResponse {

  enum ClientRegistrationResponseAction(val value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends ClientRegistrationResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends ClientRegistrationResponseAction("BAD_REQUEST")
    case Created             extends ClientRegistrationResponseAction("CREATED")

  }
  // implicit val codec: JsonValueCodec[ClientRegistrationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum ClientRegistrationResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError
        extends ClientRegistrationResponseErrorResponse("INTERNAL_SERVER_ERROR")

    case BadRequest extends ClientRegistrationResponseErrorResponse("BAD_REQUEST")
    case Created    extends ClientRegistrationResponseErrorResponse("CREATED")

  }

}
