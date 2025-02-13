package models
import io.circe.*
import io.circe.generic.semiauto.*

/** When the authorization request does not have a `display` request parameter,
  * the default value `PAGE` is used. If the display mode specified in the
  * authorization request is not supported, an error is raised.
  *
  * Values for this property are listed in "[OpenID Connect Core 1.0, 3.1.2.1.
  * Authentication
  * Request](https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest)".
  * @param value
  */
enum Display(val value: String) derives Codec.AsObject {
  case Page extends Display("PAGE")
  case Popup extends Display("POPUP")
  case Touch extends Display("TOUCH")
  case Wap extends Display("WAP")
}
