package models

import io.circe.Codec

/** Represents a dynamic scope.
  *
  * @param name
  *   The scope name.
  * @param value
  *   The scope value.
  */
final case class DynamicScope(
    name: Option[String] = None,
    value: Option[String] = None
) derives Codec.AsObject
