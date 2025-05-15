package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._
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
  *   The client associated with the response.
  */
final case class ClientRegistrationDeleteResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      ClientRegistrationDeleteResponse.ClientRegistrationDeleteResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationDeleteResponse {

  enum ClientRegistrationDeleteResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case DELETED
    case UNAUTHORIZED

  }
  // implicit val codec: Codec[ClientRegistrationDeleteResponse] = deriveCodec

  // implicit val jsoniterCodec: JsonValueCodec[ClientRegistrationDeleteResponse] =
  //   JsonCodecMaker.make(codecMakerConfig)
}

object ClientRegistrationDeleteResponseAction {
  // implicit val codec: JsonValueCodec[
  //   ClientRegistrationDeleteResponse.ClientRegistrationDeleteResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
