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
  *   The content that the authorization server implementation can use as the value of
  *   `WWW-Authenticate` header on errors.
  * @param signature
  *   The signature header of the response message.
  * @param signatureInput
  *   The signature-input header of the response message
  * @param contentDigest
  *   The content-digest header of the response message
  */
final case class UserInfoIssueResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[UserInfoIssueResponse.UserInfoIssueResponseAction],
    responseContent: Option[String],
    signature: Option[String],
    signatureInput: Option[String],
    contentDigest: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object UserInfoIssueResponse {

  enum UserInfoIssueResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends UserInfoIssueResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends UserInfoIssueResponseAction("BAD_REQUEST")
    case Unauthorized        extends UserInfoIssueResponseAction("UNAUTHORIZED")
    case Forbidden           extends UserInfoIssueResponseAction("FORBIDDEN")
    case Json                extends UserInfoIssueResponseAction("JSON")
    case Ok                  extends UserInfoIssueResponseAction("OK")

  }
  // implicit val codec: JsonValueCodec[UserInfoIssueResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum UserInfoIssueResponseErrorResponse(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends UserInfoIssueResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case BadRequest          extends UserInfoIssueResponseErrorResponse("BAD_REQUEST")
    case Unauthorized        extends UserInfoIssueResponseErrorResponse("UNAUTHORIZED")
    case Forbidden           extends UserInfoIssueResponseErrorResponse("FORBIDDEN")
    case Json                extends UserInfoIssueResponseErrorResponse("JSON")
    case Ok                  extends UserInfoIssueResponseErrorResponse("OK")

  }

}
