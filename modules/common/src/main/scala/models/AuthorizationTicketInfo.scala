package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Information about a ticket that has been issued from the {@code /auth/authorization} API.
  * @param context
  *   The arbitrary text attached to the ticket.
  */
final case class AuthorizationTicketInfo(context: String)
    derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
