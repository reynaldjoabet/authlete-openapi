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
  *   A short message which explains the result of the API call
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to the client
  *   application. Its format varies depending on the value of `action` parameter.
  */
final case class RevocationResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[RevocationResponse.RevocationResponseAction],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object RevocationResponse {

  enum RevocationResponseAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    /**
      * Authentication of the client application failed. The service implementation should return
      * either {@code "400 Bad Request"} or {@code "401 Unauthorized"} to the client application.
      */
    case INTERNAL_SERVER_ERROR

    /**
      * The request from the service was wrong or an error occurred in Authlete. The service
      * implementation should return {@code "500 Internal Server Error"} to the client application.
      */
    case INVALID_CLIENT

    /**
      * The request from the client was wrong. The service implementation should return
      * {@code "400 Bad Request"} to the client application.
      */
    case BAD_REQUEST

    /**
      * The request from the client was valid. The service implementation should return
      * {@code "200 OK"} to the client application.
      */
    case OK

  }

  // implicit val codec: JsonValueCodec[RevocationResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum RevocationResponseErrorResponse derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case INVALID_CLIENT
    case BAD_REQUEST

  }

}
