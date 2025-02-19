package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._
import sttp.tapir.Schema

/**
  * @param clientId
  *   The client ID to be deleted.
  * @param token
  *   The client registration access token.
  */
final case class ClientRegistrationDeleteRequest(
    clientId: String,
    token: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationDeleteRequest {
  // implicit val codec: Codec[ClientRegistrationDeleteRequest] = deriveCodec

  // implicit val jsoniterCodec: JsonValueCodec[ClientRegistrationDeleteRequest] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
