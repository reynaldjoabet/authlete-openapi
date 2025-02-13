package models

import io.circe.Codec

enum ServiceProfile(val value: String) derives Codec.AsObject {
  case Fapi extends ServiceProfile("FAPI")
  case OpenBanking extends ServiceProfile("OPEN_BANKING")
}
