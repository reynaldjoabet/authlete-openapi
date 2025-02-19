package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param clientSecret
  *   The new value of the client secret. Valid characters for a client secret are `A-Z`, `a-z`,
  *   `0-9`, `-`, and `_`. The maximum length of a client secret is 86.
  */
final case class ClientSecretUpdateRequest(clientSecret: String)
    derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientSecretUpdateRequest {
  // implicit val codec: JsonValueCodec[ClientSecretUpdateRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
