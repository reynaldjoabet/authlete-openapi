package models

import io.circe.Codec

enum ClaimType(value: String) derives Codec.AsObject {
  case Normal extends ClaimType("NORMAL")
  case Aggregated extends ClaimType("AGGREGATED")
  case Distributed extends ClaimType("DISTRIBUTED")
}
