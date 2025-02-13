package models

import io.circe.Codec

/** Represents a key-value pair.
  * @param key
  *   The key part.
  * @param value
  *   The value part.
  */
final case class Pair(
    key: Option[String] = None,
    value: Option[String] = None
) derives Codec.AsObject
