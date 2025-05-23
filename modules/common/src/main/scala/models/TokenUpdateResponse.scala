package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param resultCode
  *   The code which represents the result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param accessToken
  *   The access token which has been specified by the request.
  * @param accessTokenExpiresAt
  *   The date at which the access token will expire.
  * @param refreshTokenExpiresAt
  *   The date at which the refresh token will expire.
  * @param properties
  *   The extra properties associated with the access token.
  * @param scopes
  *   The scopes associated with the access token.
  * @param authorizationDetails
  *
  * @param tokenType
  *   The token type associated with the access token.
  * @param forExternalAttachment
  *   the flag which indicates whether the access token is for an external attachment.
  */
final case class TokenUpdateResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[TokenUpdateResponse.TokenUpdateResponseAction],
    accessToken: Option[String],
    accessTokenExpiresAt: Option[Long],
    refreshTokenExpiresAt: Option[Long],
    properties: List[Property] = List.empty,
    scopes: List[String] = List.empty,
    authorizationDetails: Option[AuthzDetails],
    tokenType: Option[String],
    forExternalAttachment: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenUpdateResponse {

  enum TokenUpdateResponseAction derives Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case FORBIDDEN
    case NOT_FOUND
    case OK

  }

  // implicit val codec: JsonValueCodec[TokenUpdateResponse] =
  // JsonCodecMaker.make(codecMakerConfig)
  enum TokenUpdateResponseErrorResponse derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    case INTERNAL_SERVER_ERROR
    case BAD_REQUEST
    case FORBIDDEN
    case NOT_FOUND
    case OK

  }

}
