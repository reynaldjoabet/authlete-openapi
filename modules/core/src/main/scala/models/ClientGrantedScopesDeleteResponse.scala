package models

import io.circe.Codec

/** @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  */
final case class ClientGrantedScopesDeleteResponse(
    resultCode: Option[String],
    resultMessage: Option[String]
) derives Codec.AsObject
