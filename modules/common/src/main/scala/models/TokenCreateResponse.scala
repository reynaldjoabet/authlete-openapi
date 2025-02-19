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
  *   The newly issued access token.
  * @param clientId
  *   The ID of the client application associated with the access token.
  * @param expiresAt
  *   The time at which the access token expires.
  * @param expiresIn
  *   The duration of the newly issued access token in seconds.
  * @param grantType
  *   The grant type for the newly issued access token.
  * @param properties
  *   The extra properties associated with the access token.
  * @param refreshToken
  *   The newly issued refresh token.
  * @param scopes
  *   Scopes which are associated with the access token.
  * @param subject
  *   The subject (= unique identifier) of the user associated with the newly issued access token.
  * @param tokenType
  *   The token type of the access token.
  * @param jwtAccessToken
  *   If the authorization server is configured to issue JWT-based access tokens (= if
  *   `Service.accessTokenSignAlg` is set to a `non-null` value), a JWT-based access token is issued
  *   along with the original random-string one.
  * @param authorizationDetails
  *
  * @param forExternalAttachment
  *   the flag which indicates whether the access token is for an external attachment.
  */
final case class TokenCreateResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[TokenCreateResponse.TokenCreateResponseAction],
    accessToken: Option[String],
    clientId: Option[Long],
    expiresAt: Option[Long],
    expiresIn: Option[Long],
    grantType: Option[String],
    properties: Option[List[Property]],
    refreshToken: Option[String],
    scopes: Option[List[String]],
    subject: Option[String],
    tokenType: Option[String],
    jwtAccessToken: Option[String],
    authorizationDetails: Option[AuthzDetails],
    forExternalAttachment: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenCreateResponse {

  enum TokenCreateResponseAction(value: String) derives Schema, Codec.AsObject {

    case InternalServerError extends TokenCreateResponseAction("INTERNAL_SERVER_ERROR")
    case BadRequest          extends TokenCreateResponseAction("BAD_REQUEST")
    case Forbidden           extends TokenCreateResponseAction("FORBIDDEN")
    case Ok                  extends TokenCreateResponseAction("OK")

  }

  // implicit val codec: JsonValueCodec[TokenCreateResponse] =
  // JsonCodecMaker.make(codecMakerConfig)

  enum TokenCreateResponseErrorResponse(value: String)
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case InternalServerError extends TokenCreateResponseErrorResponse("INTERNAL_SERVER_ERROR")
    case BadRequest          extends TokenCreateResponseErrorResponse("BAD_REQUEST")
    case Forbidden           extends TokenCreateResponseErrorResponse("FORBIDDEN")
    case Ok                  extends TokenCreateResponseErrorResponse("OK")

  }

}
