package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param ticket
  *   The ticket issued from Authlete `/backchannel/authentication` API.
  */
final case class BackchannelAuthenticationIssueRequest(ticket: String)
    derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object BackchannelAuthenticationIssueRequest {
  // implicit val codec: JsonValueCodec[BackchannelAuthenticationIssueRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
