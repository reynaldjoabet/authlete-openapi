package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The grant management action of the device authorization request. The `grant_management_action`
  * request parameter is defined in [Grant Management for OAuth
  * 2.0](https://openid.net/specs/fapi-grant-management.html).
  * @param value
  */
enum GrantManagementAction derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case CREATE
  case QUERY
  case REPLACE
  case REVOKE
  case MERGE

}

object GrantManagementAction {
  // implicit val codec: JsonValueCodec[GrantManagementAction] =
  // JsonCodecMaker.make(codecMakerConfig)
}
