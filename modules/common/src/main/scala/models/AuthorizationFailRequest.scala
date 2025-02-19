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

  enum AuthorizationFailRequestReason(value: String) derives Schema, Codec.AsObject {

    case Unknown            extends AuthorizationFailRequestReason("UNKNOWN")
    case NotLoggedIn        extends AuthorizationFailRequestReason("NOT_LOGGED_IN")
    case MaxAgeNotSupported extends AuthorizationFailRequestReason("MAX_AGE_NOT_SUPPORTED")
    case ExceedsMaxAge      extends AuthorizationFailRequestReason("EXCEEDS_MAX_AGE")
    case DifferentSubject   extends AuthorizationFailRequestReason("DIFFERENT_SUBJECT")
    case AcrNotSatisfied    extends AuthorizationFailRequestReason("ACR_NOT_SATISFIED")
    case Denied             extends AuthorizationFailRequestReason("DENIED")
    case ServerError        extends AuthorizationFailRequestReason("SERVER_ERROR")
    case NotAuthenticated   extends AuthorizationFailRequestReason("NOT_AUTHENTICATED")

    case AccountSelectionRequired
        extends AuthorizationFailRequestReason("ACCOUNT_SELECTION_REQUIRED")

    case ConsentRequired     extends AuthorizationFailRequestReason("CONSENT_REQUIRED")
    case InteractionRequired extends AuthorizationFailRequestReason("INTERACTION_REQUIRED")
    case InvalidTarget       extends AuthorizationFailRequestReason("INVALID_TARGET")

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
