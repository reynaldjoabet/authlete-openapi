package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.generic.semiauto._
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param clientId
  *   The client ID.
  * @param token
  *   The client registration access token.
  */
final case class ClientRegistrationGetRequest(
    clientId: String,
    token: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationGetRequest {
  // implicit val codec: Codec[ClientRegistrationGetRequest] = deriveCodec

  // implicit val jsoniterCodec: JsonValueCodec[ClientRegistrationGetRequest] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
