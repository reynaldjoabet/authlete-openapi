package models

import io.circe.Codec

/** @param requestableScopes
  *   The scopes that can be requested by the client.
  */
final case class ClientExtensionRequestableScopesGetResponse(
    requestableScopes: Option[Seq[String]]
) derives Codec.AsObject
