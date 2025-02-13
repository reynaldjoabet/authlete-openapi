package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation can use as the
  *   value of `WWW-Authenticate` header on errors.
  * @param signature
  *   The signature header of the response message.
  * @param signatureInput
  *   The signature-input header of the response message
  * @param contentDigest
  *   The content-digest header of the response message
  */
final case class UserinfoIssueResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[UserinfoIssueResponse.UserinfoIssueResponseAction],
    responseContent: Option[String],
    signature: Option[String],
    signatureInput: Option[String],
    contentDigest: Option[String]
) derives Codec.AsObject

object UserinfoIssueResponse {
  enum UserinfoIssueResponseAction(value: String) derives Codec.AsObject {
    case InternalServerError
        extends UserinfoIssueResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends UserinfoIssueResponseAction("BAD_REQUEST")
    case Unauthorized extends UserinfoIssueResponseAction("UNAUTHORIZED")
    case Forbidden extends UserinfoIssueResponseAction("FORBIDDEN")
    case Json extends UserinfoIssueResponseAction("JSON")
    case Ok extends UserinfoIssueResponseAction("OK")
  }
}
