package models

import io.circe.Codec

/** @param clientSecret
  *   The new value of the client secret. Valid characters for a client secret
  *   are `A-Z`, `a-z`, `0-9`, `-`, and `_`. The maximum length of a client
  *   secret is 86.
  */
final case class ClientSecretUpdateRequest(clientSecret: String)
    derives Codec.AsObject
