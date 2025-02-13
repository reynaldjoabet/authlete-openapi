package models

import io.circe.Codec

/** @param clientLocked
  *   The flag value to be set
  */
final case class ClientFlagUpdateRequest(
    clientLocked: Boolean
) derives Codec.AsObject
