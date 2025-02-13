package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation is to return to
  *   the client application.
  */
final case class StandardIntrospectionResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    action: Option[
      StandardIntrospectionResponse.StandardIntrospectionResponseAction
    ],
    responseContent: Option[String]
) derives Codec.AsObject

object StandardIntrospectionResponse {

  enum StandardIntrospectionResponseAction(value: String)
      derives Codec.AsObject {
    case InternalServerError
        extends StandardIntrospectionResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest extends StandardIntrospectionResponseAction("BAD_REQUEST")
    case Ok extends StandardIntrospectionResponseAction("OK")
  }
}
