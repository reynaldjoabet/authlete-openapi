package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.generic.semiauto._
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param json
  *   The client metadata in JSON format that complies with [RFC
  *   7591](https://datatracker.ietf.org/doc/html/rfc7591) (OAuth 2.0 Dynamic Client Registration
  *   Protocol).
  * @param token
  *   The client registration access token, used only for GET, UPDATE, and DELETE requests.
  * @param clientId
  *   The client's identifier, used for GET, UPDATE, and DELETE requests.
  */
final case class ClientRegistrationRequest(
    json: String,
    token: Option[String] = None,
    clientId: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientRegistrationRequest {
  // implicit val codec: Codec[ClientRegistrationRequest] = deriveCodec

  // implicit val jsoniterCodec: JsonValueCodec[ClientRegistrationRequest] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
