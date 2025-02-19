package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The authorization details. This represents the value of the `authorization_details` request
  * parameter in the preceding device authorization request which is defined in \"OAuth 2.0 Rich
  * Authorization Requests\".
  *
  * @param elements
  *   Elements of this authorization details.
  */
final case class AuthorizationDetails(
    elements: Array[AuthorizationDetailsElement]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AuthorizationDetails {
  // implicit val codec: JsonValueCodec[AuthorizationDetails] =
  // JsonCodecMaker.make(codecMakerConfig)
}
