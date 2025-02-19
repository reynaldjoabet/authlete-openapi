package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * * The client type, either `CONFIDENTIAL` or `PUBLIC`. See [RFC 6749, 2.1. Client
  * Types](https://datatracker.ietf.org/doc/html/rfc6749#section-2.1) for details.
  *
  * @param value
  */
enum ClientType(value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case Public       extends ClientType("PUBLIC")
  case Confidential extends ClientType("CONFIDENTIAL")

}

object ClientType {
  // implicit val codec: JsonValueCodec[ClientType] =
  // JsonCodecMaker.make(codecMakerConfig)
}
