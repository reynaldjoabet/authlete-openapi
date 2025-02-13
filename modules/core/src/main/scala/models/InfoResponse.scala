package models

import io.circe.Codec

/** @param version
  *   The server version.
  * @param features
  *   The list of features that the server supports.
  */
final case class InfoResponse(
    version: String,
    features: List[String]
) derives Codec.AsObject
