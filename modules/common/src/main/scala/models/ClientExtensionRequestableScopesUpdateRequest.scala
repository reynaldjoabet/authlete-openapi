package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param requestableScopes
  *   The set of scopes that the client application is allowed to request. This parameter will be
  *   one of the following. Details are described in the description. - null - an empty set - a set
  *   with at least one element If this parameter contains scopes that the service does not support,
  *   those scopes are just ignored. Also, if this parameter is `null` or is not included in the
  *   request, it is equivalent to calling `/client/extension/requestable_scopes/delete` API.
  */

final case class ClientExtensionRequestableScopesUpdateRequest(
    requestableScopes: Option[Seq[String]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientExtensionRequestableScopesUpdateRequest {
  // implicit val codec
  //     : JsonValueCodec[ClientExtensionRequestableScopesUpdateRequest] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
