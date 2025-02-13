package models

import io.circe.Codec

/** The character set for end-user verification codes (`user_code`) for Device
  * Flow.
  * @param value
  */
enum UserCodeCharset(val value: String) derives Codec.AsObject {
  case Base20 extends UserCodeCharset("BASE20")
  case Numeric extends UserCodeCharset("NUMERIC")
}
