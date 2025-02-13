package models

import io.circe.Codec

/** The grant type of the access token when the access token was created.
  *
  * @param value
  */
enum TokenType(value: String) derives Codec.AsObject {
  case UrnietfparamsoauthtokenTypejwt
      extends TokenType("urn:ietf:params:oauth:token-type:jwt")
  case UrnietfparamsoauthtokenTypeaccessToken
      extends TokenType("urn:ietf:params:oauth:token-type:access_token")
  case UrnietfparamsoauthtokenTyperefreshToken
      extends TokenType("urn:ietf:params:oauth:token-type:refresh_token")
  case UrnietfparamsoauthtokenTypeidToken
      extends TokenType("urn:ietf:params:oauth:token-type:id_token")
  case UrnietfparamsoauthtokenTypesaml1
      extends TokenType("urn:ietf:params:oauth:token-type:saml1")
  case UrnietfparamsoauthtokenTypesaml2
      extends TokenType("urn:ietf:params:oauth:token-type:saml2")
  case DeviceCode extends TokenType("DEVICE_CODE")
  case TokenExchange extends TokenType("TOKEN_EXCHANGE")
  case JwtBearer extends TokenType("JWT_BEARER")
}
