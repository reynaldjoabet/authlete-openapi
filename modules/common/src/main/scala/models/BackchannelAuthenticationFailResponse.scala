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
  *   application. Its format varies depending on the value of `action` parameter.
  */
final case class BackchannelAuthenticationFailResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      BackchannelAuthenticationFailResponse.BackchannelAuthenticationFailResponseAction
    ],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object BackchannelAuthenticationFailResponse {

  enum BackchannelAuthenticationFailResponseAction derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR

    case BAD_REQUEST
    case FORBIDDEN

  }

  enum BackchannelAuthenticationFailResponseErrorResponse derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR

    case BAD_REQUEST
    case FORBIDDEN

  }

  // implicit val codec: JsonValueCodec[BackchannelAuthenticationFailResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

}

object BackchannelAuthenticationFailResponseAction {
  // implicit val codec: JsonValueCodec[
  //   BackchannelAuthenticationFailResponse.BackchannelAuthenticationFailResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
