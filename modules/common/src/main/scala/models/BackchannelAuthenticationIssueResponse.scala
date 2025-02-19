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
  * @param authReqId
  *   The newly issued authentication request ID.
  * @param expiresIn
  *   The duration of the issued authentication request ID in seconds.
  * @param interval
  *   The minimum amount of time in seconds that the client must wait for between polling requests
  *   to the token endpoint.
  */
final case class BackchannelAuthenticationIssueResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[
      BackchannelAuthenticationIssueResponse.BackchannelAuthenticationIssueResponseAction
    ],
    responseContent: Option[String],
    authReqId: Option[String],
    expiresIn: Option[Long],
    interval: Option[Long]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object BackchannelAuthenticationIssueResponse {

  enum BackchannelAuthenticationIssueResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError
        extends BackchannelAuthenticationIssueResponseAction(
          "INTERNAL_SERVER_ERROR"
        )

    case InvalidTicket extends BackchannelAuthenticationIssueResponseAction("INVALID_TICKET")
    case Ok            extends BackchannelAuthenticationIssueResponseAction("OK")

  }

  enum BackchannelAuthenticationIssueResponseErrorResponse(value: String)
      derives Schema,
        Codec.AsObject {

    case InternalServerError
        extends BackchannelAuthenticationIssueResponseErrorResponse(
          "INTERNAL_SERVER_ERROR"
        )

    case InvalidTicket extends BackchannelAuthenticationIssueResponseErrorResponse("INVALID_TICKET")
    case Ok            extends BackchannelAuthenticationIssueResponseErrorResponse("OK")

  }
  // implicit val codec: JsonValueCodec[BackchannelAuthenticationIssueResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

}

object BackchannelAuthenticationIssueResponseAction {
  // implicit val codec: JsonValueCodec[
  //   BackchannelAuthenticationIssueResponse.BackchannelAuthenticationIssueResponseAction
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
