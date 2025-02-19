package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /vci/jwks} API.
  *
  * <p> The Authlete API is supposed to be used from within the implementation of the JWK Set
  * document endpoint of the credential issuer. </p>
  *
  * <p> Authlete's {@code /vci/jwks} API returns JSON which can be mapped to this class. The
  * credential issuer implementation should retrieve the value of the <code>{@link #getAction()
  * action}</code> response parameter from the API response and take the following steps according
  * to the value. </p>
  *
  * <h3><code>OK</code></h3>
  *
  * <p> When the value of the <code>{@link #getAction() action}</code> response parameter is
  * <code>{@link Action#OK OK}</code>, it means that Authlete could prepare the JWK Set document
  * successfully. <p>
  *
  * </p> In this case, the implementation of the JWK Set document endpoint of the credential issuer
  * should return an HTTP response with the HTTP status code "{@code 200 OK}" and the content type
  * "{@code application/json}". The message body of the response has been prepared by Authlete's
  * {@code /vci/jwks} API and it is available as the <code>{@link #getResponseContent()
  * responseContent}</code> response parameter. </p>
  *
  * <p> The implementation of the JWK Set document endpoint can construct an HTTP response by doing
  * like below. </p>
  *
  * <pre style="border: solid 1px black; padding: 0.5em;"> 200 OK Content-Type: application/json
  * (Other HTTP headers)
  *
  * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
  *
  * <h3><code>NOT_FOUND</code></h3>
  *
  * <p> When the value of the <code>{@link #getAction() action}</code> response parameter is
  * <code>{@link Action#NOT_FOUND NOT_FOUND}</code>, it means that the service configuration has not
  * enabled the feature of Verifiable Credentials and so the JWK Set document endpoint should not be
  * accessed. </p>
  *
  * </p> In this case, the implementation of the JWK Set document endpoint of the credential issuer
  * should return an HTTP response with the HTTP status code "{@code 404 Not Found}" and the content
  * type "{@code application/json}". The message body (= error information in the JSON format) of
  * the response has been prepared by Authlete's {@code /vci/jwks} API and it is available as the
  * <code>{@link #getResponseContent() responseContent}</code> response parameter. </p>
  *
  * <p> The implementation of the JWK Set document endpoint can construct an HTTP response by doing
  * like below. </p>
  *
  * <pre style="border: solid 1px black; padding: 0.5em;"> 404 Not Found Content-Type:
  * application/json (Other HTTP headers)
  *
  * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
  *
  * <h3><code>INTERNAL_SERVER_ERROR</code></h3>
  *
  * <p> When the value of the <code>{@link #getAction() action}</code> response parameter is
  * <code>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</code>, it means that an
  * unexpected error has occurred on Authlete side or the service has not been set up properly yet.
  * </p>
  *
  * </p> In this case, a simple implementation of the JWK Set document endpoint would return an HTTP
  * response with the HTTP status code "{@code 500 Internal Server Error}" and the content type
  * "{@code application/json}". The message body (= error information in the JSON format) of the
  * response has been prepared by Authlete's {@code /vci/jwks} API and it is available as the
  * <code>{@link #getResponseContent() responseContent}</code> response parameter. </p>
  *
  * <p> Such simple implementation of the JWK Set document endpoint can construct an HTTP response
  * by doing like below. </p>
  *
  * <pre style="border: solid 1px black; padding: 0.5em;"> 500 Internal Server Error Content-Type:
  * application/json (Other HTTP headers)
  *
  * <i>(the value of the {@link #getResponseContent() responseContent} response parameter)</i></pre>
  *
  * <p> However, in real commercial deployments, it is rare for a credential issuer to return
  * "{@code 500 Internal Server Error}" when it encounters an unexpected internal error. It's up to
  * implementations of credential issuers what they actually return in the case of internal server
  * error. </p>
  * @param resultCode
  *   The result of the {@code /vci/jwks} API call.
  * @param resultMessage
  *   A message which explains the result of the API call.
  * @param action
  *   The next action that the implementation of the JWK Set document endpoint of the credential
  *   issuer should take.
  * @param responseContent
  *   The content of the response that should be returned from the JWK Set
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */

final case class CredentialIssuerJwksResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[CredentialIssuerJwksResponse.CredentialIssuerJwksResponseActionAction] = None,
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object CredentialIssuerJwksResponse {

  enum CredentialIssuerJwksResponseActionAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case OK
    case NOT_FOUND
    case INTERNAL_SERVER_ERROR

  }

}
