package models

import io.circe.Codec

/** @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param newClientSecret
  *   The new client secret.
  * @param oldClientSecret
  *   The old client secret.
  */
final case class ClientSecretUpdateResponse(
    resultCode: Option[String] = None,
    resultMessage: Option[String] = None,
    newClientSecret: Option[String] = None,
    oldClientSecret: Option[String] = None
) derives Codec.AsObject
