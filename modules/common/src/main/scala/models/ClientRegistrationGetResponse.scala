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
  *   The client details returned by the authorization server.
  */
final case class ClientRegistrationGetResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      ClientRegistrationGetResponse.ClientRegistrationGetResponseAction
    ] = None,
    responseContent: Option[String] = None,
    client: Option[Client] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationGetResponse {

  enum ClientRegistrationGetResponseAction derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case OK
    case UNAUTHORIZED

  }
  // implicit val codec: Codec[ClientRegistrationGetResponse] = deriveCodec
  // implicit val jsoniterCodec: JsonValueCodec[ClientRegistrationGetResponse] =
  //   JsonCodecMaker.make(codecMakerConfig)

  enum ClientRegistrationGetResponseErrorResponse derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case UNAUTHORIZED

  }

}
object ClientRegistrationGetResponseAction {
  // implicit val codec: JsonValueCodec[
  //   ClientRegistrationGetResponse.ClientRegistrationGetResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
