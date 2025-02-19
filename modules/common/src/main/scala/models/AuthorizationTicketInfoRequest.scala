package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's `/auth/authorization/ticket/update` API.
  *
  * The API is used to update the content of a ticket that has been issued from the
  * `/auth/authorization` API.
  * @param ticket
  *   The ticket that has been issued from the `/auth/authorization` API.
  */
final case class AuthorizationTicketInfoRequest(
    ticket: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
