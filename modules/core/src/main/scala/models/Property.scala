package models

import io.circe.Codec

/** @param key
  *   The key part of the property.
  * @param value
  *   The value part of the property.
  * @param hidden
  *   A flag to indicate whether this property is hidden from or visible to
  *   client applications. If `true`, this property is hidden from client
  *   applications. Otherwise, it is visible.
  */
final case class Property(
    key: Option[String] = None,
    value: Option[String] = None,
    hidden: Option[Boolean] = None
) derives Codec.AsObject
