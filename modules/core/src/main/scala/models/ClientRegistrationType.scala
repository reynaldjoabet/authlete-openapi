package models

import io.circe.Codec

/** @param value
  *   * Values for the `client_registration_types` RP metadata and the
  *   `client_registration_types_supported` OP metadata that are defined in
  *   [OpenID Connect Federation
  *   1.0](https://openid.net/specs/openid-connect-federation-1_0.html).
  */
enum ClientRegistrationType(value: String) derives Codec.AsObject {
  case Automatic extends ClientRegistrationType("AUTOMATIC")
  case Explicit extends ClientRegistrationType("EXPLICIT")

}
