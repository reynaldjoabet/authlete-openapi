package models

import io.circe.Codec

enum DeliveryMode(value: String) derives Codec.AsObject {
  case Ping extends DeliveryMode("PING")
  case Poll extends DeliveryMode("POLL")
  case Push extends DeliveryMode("PUSH")
}
