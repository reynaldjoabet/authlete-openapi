package models

import io.circe.Codec

/** @param ticket
  *   The ticket issued from Authlete `/auth/token` API.
  * @param reason
  *   The reason of the failure of the token request.
  */
final case class TokenFailRequest(
    ticket: String,
    reason: TokenFailRequest.TokenFailRequestReason
) derives Codec.AsObject

object TokenFailRequest {

  enum TokenFailRequestReason(value: String) derives Codec.AsObject {
    case Unknown extends TokenFailRequestReason("UNKNOWN")
    case InvalidResourceOwnerCredentials
        extends TokenFailRequestReason("INVALID_RESOURCE_OWNER_CREDENTIALS")
    case InvalidTarget extends TokenFailRequestReason("INVALID_TARGET")
  }
}
