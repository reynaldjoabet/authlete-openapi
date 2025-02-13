package models

import io.circe.Codec

/** @param count
  *   The number of tokens revoked
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  */
final case class TokenRevokeResponse(
    count: Option[Int],
    resultCode: Option[String],
    resultMessage: Option[String]
) derives Codec.AsObject
