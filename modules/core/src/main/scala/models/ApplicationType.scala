package models

import io.circe.Codec

/** The application type. The value of this property affects the validation
  * steps for a redirect URI. See the description about `redirectUris` property
  * for more details.
  */

enum ApplicationType(value: String) derives Codec.AsObject {
  case Web extends ApplicationType("WEB")
  case Native extends ApplicationType("NATIVE")
}
