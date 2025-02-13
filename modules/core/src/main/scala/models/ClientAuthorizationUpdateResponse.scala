package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  */
final case class ClientAuthorizationUpdateResponse(
    resultCode: Option[String],
    resultMessage: Option[String]
) derives Codec.AsObject
