package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param ticket
  *   The ticket issued from Authlete `/auth/authorization` API.
  * @param reason
  *   The reason of the failure of the authorization request. For more details, see [NO_INTERACTION]
  *   in the description of `/auth/authorization` API.
  * @param description
  *   The custom description about the authorization failure.
  */
final case class AuthorizationFailRequest(
    ticket: String,
    reason: AuthorizationFailRequest.AuthorizationFailRequestReason,
    description: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AuthorizationFailRequest {

  enum AuthorizationFailRequestReason derives Schema, Codec.AsObject {

    case UNKNOWN
    case NOT_LOGGED_IN
    case MAX_AGE_NOT_SUPPORTED
    case EXCEEDS_MAX_AGE
    case DIFFERENT_SUBJECT
    case ACR_NOT_SATISFIED
    case DENIED
    case SERVER_ERROR
    case NOT_AUTHENTICATED

    case ACCOUNT_SELECTION_REQUIRED

    case CONSENT_REQUIRED
    case INTERACTION_REQUIRED
    case INVALID_TARGET
    case LOGIN_REQUIRED
    case REGISTRATION_REQUIRED
    case TERMS_OF_SERVICE_REQUIRED
    case ACCOUNT_LOCKED
    case ACCOUNT_DISABLED
    case ACCOUNT_TEMPORARILY_LOCKED
    case ACCOUNT_PERMANENTLY_LOCKED
    case ACCOUNT_COMPROMISED
    case ACCOUNT_NOT_VERIFIED
    case ACCOUNT_SUSPENDED
    case ACCOUNT_CLOSED

  }

  // implicit val codec: JsonValueCodec[AuthorizationFailRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}

object AuthorizationFailRequestReason {
  // implicit val codec: JsonValueCodec[
  //   AuthorizationFailRequest.AuthorizationFailRequestReason
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
