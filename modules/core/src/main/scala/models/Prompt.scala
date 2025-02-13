package models

import io.circe.Codec

import io.circe.Codec

/** The prompt that the UI displayed to the end-user must satisfy as the minimum
  * level. This value comes from `prompt` request parameter. When the
  * authorization request does not contain `prompt` request parameter, `CONSENT`
  * is used as the default value. See "[OpenID Connect Core 1.0, 3.1.2.1.
  * Authentication
  * Request](https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest),
  * prompt" for `prompt` request parameter.
  * @param value
  */
enum Prompt(val value: String) derives Codec.AsObject {
  case None extends Prompt("NONE")
  case Login extends Prompt("LOGIN")
  case Consent extends Prompt("CONSENT")
  case SelectAccount extends Prompt("SELECT_ACCOUNT")
}
