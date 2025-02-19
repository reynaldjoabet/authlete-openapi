package common.models

import com.authlete.common.dto.IDTokenReissueRequest
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * A request to Authlete's {@code /idtoken/reissue} API.
  *
  * <p> The API is expected to be called only when the value of the "{@code action}" parameter in a
  * response from the {@code /auth/token} API is {@link TokenResponse.Action#ID_TOKEN_REISSUABLE
  * ID_TOKEN_REISSUABLE}. The purpose of the {@code /idtoken/reissue} API is to generate a token
  * response that includes a new ID token together with a new access token and a refresh token. </p>
  *
  * <p> Regarding the preparation for the API call, see the description of the {@link TokenResponse}
  * class. </p>
  *
  * @see
  *   <a href="https://openid.net/specs/openid-connect-core-1_0.html#RefreshTokens" >OpenID Connect
  *   Core 1.0, 12.2. Successful Refresh Response</a>
  *
  * @see
  *   TokenResponse
  *
  * @see
  *   IDTokenReissueResponse
  *
  * @param accessToken
  *   The access token that the client application presented to the authorization server. This
  *   parameter is mandatory.
  * @param refreshToken
  * @param sub
  * @param claims
  * @param idtHeaderParams
  * @param idTokenAudType
  * @param idToken
  */

final case class IDTokenReissueRequest(
    accessToken: Option[String],
    refreshToken: Option[String],
    sub: Option[String],
    claims: Option[String],
    idtHeaderParams: Option[String],
    idTokenAudType: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
