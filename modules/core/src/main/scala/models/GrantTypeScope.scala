package models

import io.circe.Codec

/** Represents a grant scope for OAuth 2.0.
  *
  * @param scope
  *   Space-delimited scopes.
  * @param resource
  *   List of resource indicators.
  */
final case class GrantScope(
    scope: Option[String] = None,
    resource: Option[List[String]] = None
) derives Codec.AsObject
