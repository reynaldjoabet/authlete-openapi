package models

import io.circe.Codec

/** The authorization details. This represents the value of the
  * `authorization_details` request parameter in the preceding device
  * authorization request which is defined in \"OAuth 2.0 Rich Authorization
  * Requests\".
  * @param elements
  *   Elements of this authorization details.
  */
final case class AuthzDetails(
    elements: Option[List[AuthorizationDetailsElement]] = None
) derives Codec.AsObject
