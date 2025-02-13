package models

import io.circe.Codec

/** @param tag
  *   The language tag part.
  * @param value
  *   The value part.
  */
final case class TaggedValue(
    tag: Option[String],
    value: Option[String]
) derives Codec.AsObject
