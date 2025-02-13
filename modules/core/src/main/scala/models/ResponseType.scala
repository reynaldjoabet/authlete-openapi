package models

import io.circe.Codec

enum ResponseType(value: String) derives Codec.AsObject {
  case None extends ResponseType("NONE")
  case Code extends ResponseType("CODE")
  case Token extends ResponseType("TOKEN")
  case IdToken extends ResponseType("ID_TOKEN")
  case CodeToken extends ResponseType("CODE_TOKEN")
  case CodeIdToken extends ResponseType("CODE_ID_TOKEN")
  case IdTokenToken extends ResponseType("ID_TOKEN_TOKEN")
  case CodeIdTokenToken extends ResponseType("CODE_ID_TOKEN_TOKEN")

}
