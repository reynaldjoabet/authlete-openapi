package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  */
final case class ClientFlagUpdateResponse(
    resultCode: String,
    resultMessage: String
) derives Codec.AsObject
