package models

import io.circe.Codec

/** @param ticket
  *   The ticket issued from Authlete `/backchannel/authentication` API.
  */
final case class BackchannelAuthenticationIssueRequest(ticket: String)
    derives Codec.AsObject
