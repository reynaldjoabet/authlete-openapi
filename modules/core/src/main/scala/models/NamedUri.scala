package models

import io.circe.Codec

import io.circe.Codec

/** Represents a named URI.
  * @param name
  *   The name associated with the URI.
  * @param uri
  *   The URI itself.
  */
final case class NamedUri(
    name: Option[String] = None,
    uri: Option[String] = None
) derives Codec.AsObject
