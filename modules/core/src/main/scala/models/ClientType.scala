package models

import io.circe.Codec

/** * The client type, either `CONFIDENTIAL` or `PUBLIC`. See [RFC 6749, 2.1.
  * Client Types](https://datatracker.ietf.org/doc/html/rfc6749#section-2.1) for
  * details.
  *
  * @param value
  */
enum ClientType(value: String) derives Codec.AsObject {
  case Public extends ClientType("PUBLIC")
  case Confidential extends ClientType("CONFIDENTIAL")

}
