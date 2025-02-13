package models

import io.circe.Codec

/** The grant type of the access token when the access token was created.
  * @param value
  */

enum GrantType(value: String) derives Codec.AsObject {
  case AuthorizationCode extends GrantType("AUTHORIZATION_CODE")
  case Implicit extends GrantType("IMPLICIT")
  case Password extends GrantType("PASSWORD")
  case ClientCredentials extends GrantType("CLIENT_CREDENTIALS")
  case RefreshToken extends GrantType("REFRESH_TOKEN")
  case Ciba extends GrantType("CIBA")
  case DeviceCode extends GrantType("DEVICE_CODE")
  case TokenExchange extends GrantType("TOKEN_EXCHANGE")
  case JwtBearer extends GrantType("JWT_BEARER")
}
