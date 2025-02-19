package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from the {@code /vci/deferred/parse} API.
  *
  * <p> The response from the Authlete API is a JSON object and can be mapped to this class. The API
  * caller should retrieve the value of the {@code action} parameter from the API response and take
  * one of the following actions accordingly. </p>
  *
  * <hr> <h3>{@code action} = {@link Action#OK OK}</h3>
  *
  * <p> The {@code action} value {@link Action#OK OK} means that the deferred credential request is
  * valid. In this case, the implementation of the deferred credential endpoint should call the
  * {@code /vci/deferred/issue} API in order to issue a verifiable credential, or return the
  * {@code issuance_pending} error if the verifiable credential is not ready yet. </p>
  *
  * <p> The following is an example error response telling the request sender that the verifiable
  * credential is not ready yet. </p>
  *
  * <pre> HTTP/1.1 400 Bad Request Content-Type: application/json Cache-Control: no-store
  *
  * { "error": "issuance_pending" } </pre>
  *
  * <hr> <h3>{@code action} = {@link Action#BAD_REQUEST BAD_REQUEST}</h3>
  *
  * <p> The {@code action} value {@link Action#BAD_REQUEST BAD_REQUEST} means that the deferred
  * credential request is invalid. In this case, the implementation of the deferred credential
  * endpoint should return an error response to the request sender. The HTTP status code and the
  * content type of the error response should be 400 and {@code application/json}, respectively. The
  * value of the {@code responseContent} parameter can be used as the message body of the error
  * response as it conforms to the specification of "Deferred Credential Error Response". </p>
  *
  * <pre> HTTP/1.1 400 Bad Request Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <hr> <h3>{@code action} = {@link Action#UNAUTHORIZED UNAUTHORIZED}</h3>
  *
  * <p> The {@code action} value {@link Action#UNAUTHORIZED UNAUTHORIZED} means that the access
  * token is invalid. In this case, the implementation of the deferred credential endpoint should
  * return an error response to the request sender. The HTTP status code of the error response
  * should be 401. The value of the {@code responseContent} parameter can be used as the value of
  * the {@code WWW-Authenticate} HTTP header of the error response. </p>
  *
  * <pre> HTTP/1.1 401 Unauthorized WWW-Authenticate: (Put the value of the "responseContent"
  * parameter here.) </pre>
  *
  * <p> Note that the implementation of the deferred credential endpoint should call the
  * {@code /auth/introspection} API to check whether the access token is valid BEFORE calling the
  * {@code /vci/deferred/parse} API. The validation on the access token by the
  * {@code /vci/deferred/parse} API is limited and not exhaustive. For example, the
  * {@code /vci/deferred/parse} API does not check certificate binding (<a
  * href="https://www.rfc-editor.org/rfc/rfc8705.html" >RFC 8705</a>). </p>
  *
  * <hr> <h3>{@code action} = {@link Action#FORBIDDEN FORBIDDEN}</h3>
  *
  * <p> The {@code action} value {@link Action#FORBIDDEN FORBIDDEN} means that the use of the
  * Authlete API is forbidden. In this case, the implementation of the deferred credential endpoint
  * should return an error response to the request sender. The HTTP status code and the content type
  * of the error response should be 403 and {@code application/json}, respectively. The value of the
  * {@code responseContent} parameter can be used as the message body of the error response as it
  * conforms to the specification of "Deferred Credential Error Response". </p>
  *
  * <pre> HTTP/1.1 403 Forbidden Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <p> Note that this happens either when the {@link Service#isVerifiableCredentialsEnabled()
  * verifiableCredentialsEnabled} property of the {@link Service service} is false or when the
  * Authlete server does not support the feature of "Verifiable Credentials". In either case, this
  * "forbidden" issue should be solved before the service is deployed in a production environment.
  * </p>
  *
  * <hr> <h3>{@code action} = {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</h3>
  *
  * <p> The {@code action} value {@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} means
  * that something wrong happened on Authlete side. In this case, the implementation of the deferred
  * credential endpoint should return an error response to the request sender. The HTTP status code
  * and the content type of the error response should be 500 and {@code application/json},
  * respectively. The value of the {@code responseContent} parameter can be used as the message body
  * of the error response as it conforms to the specification of "Deferred Credential Error
  * Response". </p>
  *
  * <pre> HTTP/1.1 500 Internal Server Error Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <p> Note that, however, in real production deployments, it may be better to return a vaguer
  * error response instead of a bare one like above. </p>
  * @param resultCode
  *   The result code of the API call.
  * @param resultMessage
  *   The result message of the API call.
  * @param action
  *   The next action that the deferred credential endpoint should take.
  * @param responseContent
  *   The content of the response to the request sender.
  * @param info
  *   Information about the credential request bound to the transaction ID.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */

final case class CredentialDeferredParseResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[CredentialDeferredParseResponse.CredentialDeferredParseResponseAction] = None,
    responseContent: Option[String],
    info: Option[CredentialRequestInfo] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object CredentialDeferredParseResponse {

  /**
    * The next action that the deferred credential endpoint should take.
    */
  enum CredentialDeferredParseResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    /**
      * The deferred credential request is valid.
      */
    case OK

    /**
      * The deferred credential request is invalid.
      */
    case BAD_REQUEST

    /**
      * The deferred credential request does not contain the access token or the access token is
      * invalid.
      */

    case UNAUTHORIZED

    /**
      * The feature of Verifiable Credentials is not enabled in the service configuration.
      */

    case FORBIDDEN

    /**
      * An error occurred on Authlete side.
      */

    case INTERNAL_SERVER_ERROR

  }

}
