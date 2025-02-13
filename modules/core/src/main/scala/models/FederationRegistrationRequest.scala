package models

import io.circe.Codec

/** @param entityConfiguration
  *   The entity configuration of a relying party.
  * @param trustChain
  *   The trust chain of a relying party.
  */
final case class FederationRegistrationRequest(
    entityConfiguration: Option[String] = None,
    trustChain: Option[String] = None
) derives Codec.AsObject
