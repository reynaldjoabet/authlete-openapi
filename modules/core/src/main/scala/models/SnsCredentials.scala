package models

import io.circe.Codec

/** @param sns
  *   SNS.
  * @param apiKey
  *   API key.
  * @param apiSecret
  *   API secret.
  */
final case class SnsCredentials(
    sns: Option[String],
    apiKey: Option[String],
    apiSecret: Option[String]
) derives Codec.AsObject
