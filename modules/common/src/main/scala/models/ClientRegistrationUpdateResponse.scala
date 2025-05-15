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
  *   The client associated with the update.
  */
final case class ClientRegistrationUpdateResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      ClientRegistrationUpdateResponse.ClientRegistrationUpdateResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationUpdateResponse {

  enum ClientRegistrationUpdateResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case UPDATED
    case UNAUTHORIZED

  }
  // implicit val codec: JsonValueCodec[ClientRegistrationUpdateResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum ClientRegistrationUpdateResponseErrorResponse(val value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError
        extends ClientRegistrationUpdateResponseErrorResponse("INTERNAL_SERVER_ERROR")

    case BadRequest   extends ClientRegistrationUpdateResponseErrorResponse("BAD_REQUEST")
    case Updated      extends ClientRegistrationUpdateResponseErrorResponse("UPDATED")
    case Unauthorized extends ClientRegistrationUpdateResponseErrorResponse("UNAUTHORIZED")

  }

}

object ClientRegistrationUpdateResponseAction {
  // implicit val codec: JsonValueCodec[
  //   ClientRegistrationUpdateResponse.ClientRegistrationUpdateResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
