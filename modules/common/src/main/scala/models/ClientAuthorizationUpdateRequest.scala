package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param subject
  *   The subject (= unique identifier) of the end-user who has granted authorization to the client
  *   application.
  * @param scopes
  *   An array of new scopes. Optional. If a non-null value is given, the new scopes are set to all
  *   existing access tokens. If an API call is made using
  *   `\"Content-Type: application/x-www-form-urlencoded\"`, scope names listed in this request
  *   parameter should be delimited by spaces (after form encoding, spaces are converted to `+`).
  */
final case class ClientAuthorizationUpdateRequest(
    subject: String,
    scopes: Option[List[String]] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientAuthorizationUpdateRequest {
  // implicit val codec: JsonValueCodec[ClientAuthorizationUpdateRequest] =
  // JsonCodecMaker.make(codecMakerConfig)

  implicit val clientAuthorizationUpdateRequestCodec
      : JsonValueCodec[Option[ClientAuthorizationUpdateRequest]] =
    JsonCodecMaker.make(codecMakerConfig)

}
