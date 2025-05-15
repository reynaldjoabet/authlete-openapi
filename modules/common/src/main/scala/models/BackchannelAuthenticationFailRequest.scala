package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param ticket
  *   The reason of the failure of the backchannel authentication request. This request parameter is
  *   not mandatory but optional. However, giving this parameter is recommended. If omitted,
  *   `SERVER_ERROR` is used as a reason.
  * @param reason
  *   The reason of the failure of the backchannel authentication request. This request parameter is
  *   not mandatory but optional. However, giving this parameter is recommended. If omitted,
  *   `SERVER_ERROR` is used as a reason.
  * @param errorDescription
  *   The description of the error. This corresponds to the `error_description` property in the
  *   response to the client.
  * @param errorUri
  *   The URI of a document which describes the error in detail. If this optional request parameter
  *   is given, its value is used as the value of the `error_uri` property.
  */
final case class BackchannelAuthenticationFailRequest(
    ticket: String,
    reason: BackchannelAuthenticationFailRequest.BackchannelAuthenticationFailRequestReason,
    errorDescription: Option[String] = None,
    errorUri: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object BackchannelAuthenticationFailRequest {

  enum BackchannelAuthenticationFailRequestReason derives Schema, Codec.AsObject {

    case ACCESS_DENIED

    case EXPIRED_LOGIN_HINT_TOKEN

    case INVALID_BINDING_MESSAGE

    case INVALID_TARGET
    case INVALID_USER_CODE
    case MISSING_USER_CODE
    case SERVER_ERROR

    case UNAUTHORIZED_CLIENT

    case UNKNOWN_USER_ID

  }

  // implicit val codec: JsonValueCodec[BackchannelAuthenticationFailRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}

object BackchannelAuthenticationFailRequestAction {
  // implicit val codec: JsonValueCodec[
  //   BackchannelAuthenticationFailRequest.BackchannelAuthenticationFailRequestReason
  // ] =
  //   JsonCodecMaker.make(codecMakerConfig)
}
