package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * A response from Authlete's {@code /idtoken/reissue} API.
  *
  * <p> A response from the {@code /idtoken/reissue} API can be mapped to this class. The API caller
  * should extract the value of the "{@code action}" parameter from the API response and take the
  * next action based on the value of the parameter. </p>
  *
  * <hr> <h3>{@code action} = {@link Action#OK OK}</h3>
  *
  * <p> The {@code action} value {@link Action#OK OK} means that an ID token has been reissued
  * successfully. In this case, the implementation of the token endpoint should return a successful
  * response to the client application. The HTTP status code and the content type of the response
  * should be 200 and {@code application/json}, respectively. The value of the
  * {@code responseContent} parameter can be used as the message body of the response.
  *
  * <pre> HTTP/1.1 200 OK Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <hr> <h3>{@code action} = {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</h3>
  *
  * <p> The {@code action} value {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} means
  * that something wrong happened on Authlete side. In this case, the implementation of the token
  * endpoint should return an error response to the client application. The HTTP status code and the
  * content type of the error response should be 500 and {@code application/json}, respectively. The
  * value of the {@code responseContent} parameter can be used as the message body of the error
  * response. </p>
  *
  * <pre> HTTP/1.1 500 Internal Server Error Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <p> Note that, however, in real production deployments, it may be better to return a vaguer
  * error response instead of a bare one like above. </p>
  *
  * <hr> <h3>{@code action} = {@link Action#CALLER_ERROR CALLER_ERROR}</h3>
  *
  * <p> The {@code action} value {@link Action#CALLER_ERROR CALLER_ERROR} means that the API call is
  * wrong. For example, the "{@code accessToken}" request parameter is missing. </p>
  *
  * <p> Caller errors should be solved before the service is deployed in a production environment.
  * </p>
  *
  * @see
  *   <a href="https://openid.net/specs/openid-connect-core-1_0.html#RefreshTokens" >OpenID Connect
  *   Core 1.0, 12.2. Successful Refresh Response</a>
  *
  * @see
  *   IDTokenReissueRequest
  *
  * @param resultCode
  *   The result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param responseContent
  *   The content that the authorization server implementation can use as the value of
  * @param idToken
  */

final case class IDTokenReissueResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[IDTokenReissueResponse.IDTokenReissueResponseAction],
    responseContent: Option[String] = None,
    idToken: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object IDTokenReissueResponse {

  enum IDTokenReissueResponseAction derives Schema, Codec.AsObject {

    /**
      * The ID token has been reissued successfully.
      */
    case OK

    /**
      * An error occurred on Authlete side.
      */
    case INTERNAL_SERVER_ERROR

    /**
      * The API call was wrong. For example, the {@code accessToken} request parameter was missing.
      */
    case CALLER_ERROR

  }

}
