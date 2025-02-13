package models

import io.circe.Codec

enum Sns(value: String) derives Codec.AsObject {
  case Facebook extends Sns("FACEBOOK")
}
