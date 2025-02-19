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
  *   The code which represents the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  */
final case class AuthorizationFailResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[AuthorizationFailResponse.AuthorizationFailResponseAction],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AuthorizationFailResponse {

  enum AuthorizationFailResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends AuthorizationFailResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends AuthorizationFailResponseAction("BAD_REQUEST")
    case Location            extends AuthorizationFailResponseAction("LOCATION")
    case Form                extends AuthorizationFailResponseAction("FORM")

  }

  enum AuthorizationFailResponseErrorResponse(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends AuthorizationFailResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case BadRequest          extends AuthorizationFailResponseErrorResponse("BAD_REQUEST")
    case Location            extends AuthorizationFailResponseErrorResponse("LOCATION")
    case Form                extends AuthorizationFailResponseErrorResponse("FORM")

  }

  // implicit val codec: JsonValueCodec[AuthorizationFailResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
}

object AuthorizationFailResponseAction {
  // implicit val codec: JsonValueCodec[
  //   AuthorizationFailResponse.AuthorizationFailResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
