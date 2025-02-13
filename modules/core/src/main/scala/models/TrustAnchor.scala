package models

import io.circe.Codec

/** @param entityId
  *   the entity ID of the trust anchor
  * @param jwks
  *   the JWK Set document containing public keys of the trust anchor
  */
final case class TrustAnchor(
    entityId: Option[String] = None,
    jwks: Option[String] = None
) derives Codec.AsObject
