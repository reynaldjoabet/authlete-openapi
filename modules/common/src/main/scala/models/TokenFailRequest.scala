package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param ticket
  *   The ticket issued from Authlete `/auth/token` API.
  * @param reason
  *   The reason of the failure of the token request.
  */
final case class TokenFailRequest(
    ticket: String,
    reason: TokenFailRequest.TokenFailRequestReason
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenFailRequest {

  enum TokenFailRequestReason(value: String) derives Schema, Codec.AsObject {

    case Unknown extends TokenFailRequestReason("UNKNOWN")

    case InvalidResourceOwnerCredentials
        extends TokenFailRequestReason("INVALID_RESOURCE_OWNER_CREDENTIALS")

    case InvalidTarget extends TokenFailRequestReason("INVALID_TARGET")

  }

  // implicit val codec: JsonValueCodec[TokenFailRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
