package models

import io.circe.Codec

/** @param scopes
  * @param claims
  *   The claims associated with the Grant.
  * @param authorizationDetails
  */
case class Grant(
    scopes: Option[List[GrantScope]] = None,
    claims: Option[List[String]] = None,
    authorizationDetails: Option[AuthzDetails] = None
) derives Codec.AsObject
