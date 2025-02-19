package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param entityConfiguration
  *   The entity configuration of a relying party.
  * @param trustChain
  *   The trust chain of a relying party.
  */
final case class FederationRegistrationRequest(
    entityConfiguration: Option[String] = None,
    trustChain: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object FederationRegistrationRequest {
  // implicit val codec: JsonValueCodec[FederationRegistrationRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
