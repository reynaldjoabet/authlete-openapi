package models

import io.circe.Codec

/** The grant management action of the device authorization request. The
  * `grant_management_action` request parameter is defined in [Grant Management
  * for OAuth 2.0](https://openid.net/specs/fapi-grant-management.html).
  * @param value
  */
enum GrantManagementAction(val value: String) {
  case Create extends GrantManagementAction("CREATE")
  case Query extends GrantManagementAction("QUERY")
  case Replace extends GrantManagementAction("REPLACE")
  case Revoke extends GrantManagementAction("REVOKE")
  case Merge extends GrantManagementAction("MERGE")
}

object GrantManagementAction {
  import io.circe.Codec
  import io.circe.generic.semiauto._

  implicit val grantManagementActionCodec: Codec[GrantManagementAction] =
    deriveCodec
}
