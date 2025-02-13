package models

import io.circe.Codec

/** @param name
  *   The name of the scope.
  * @param defaultEntry
  *   `true` to mark the scope as default. Scopes marked as default are regarded
  *   as requested when an authorization request from a client application does
  *   not contain scope request parameter.
  * @param description
  *   The description about the scope.
  * @param descriptions
  *   The descriptions about this scope in multiple languages.
  * @param attributes
  *   The attributes of the scope.
  */
final case class Scope(
    name: Option[String],
    defaultEntry: Option[Boolean],
    description: Option[String],
    descriptions: Option[Array[TaggedValue]],
    attributes: Option[Array[Pair]]
) derives Codec.AsObject
