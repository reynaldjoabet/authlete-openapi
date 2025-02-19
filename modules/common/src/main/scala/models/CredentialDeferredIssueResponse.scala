package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * A response from Authlete's {@code /vci/deferred/issue} API.
  *
  * <p> A response from the {@code /vci/deferred/issue} API can be mapped to this class. The API
  * caller should extract the value of the {@code action} parameter from the API response and take
  * the next action based on the value of the parameter. </p>
  *
  * <hr> <h3>{@code action} = {@link Action#OK OK}</h3>
  *
  * <p> The {@code action} value {@link Action#OK OK} means that a credential has been issued
  * successfully. In this case, the implementation of the deferred credential endpoint should return
  * a successful response to the request sender. The HTTP status code and the content type of the
  * response should be 200 and {@code application/json}, respectively. The value of the
  * {@code responseContent} parameter can be used as the message body of the response. It contains
  * the "{@code credential}" parameter that conforms to the specification of "Deferred Credential
  * Response". </p>
  *
  * <pre> HTTP/1.1 200 OK Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <hr> <h3>{@code action} = {@link Action#OK_JWT OK_JWT}</h3>
  *
  * <p> The {@code action} value {@link Action#OK_JWT OK_JWT} means that a credential has been
  * issued successfully and the deferred credential response should be encrypted. In this case, the
  * implementation of the deferred credential endpoint should return a successful response to the
  * request sender. The HTTP status code and the content type of the response should be 200 and
  * {@code application/jwt}, respectively. The value of the {@code responseContent} parameter is an
  * encrypted JWT and can be used as the message body of the response. </p>
  *
  * <pre> HTTP/1.1 200 OK Content-Type: application/jwt Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
  *
  * <hr> <h3>{@code action} = {@link Action#BAD_REQUEST BAD_REQUEST}</h3>
  *
  * <p> The {@code action} value {@link Action#BAD_REQUEST BAD_REQUEST} means that the original
  * deferred credential request is wrong. In this case, the implementation of the deferred
  * credential endpoint should return an error response to the request sender. The HTTP status code
  * and the content type of the error response should be 400 and {@code application/json},
  * respectively. The value of the {@code responseContent} parameter can be used as the message body
  * of the error response as it conforms to the specification of "Deferred Credential Error
  * Response". </p>
  *
  * <pre> HTTP/1.1 400 Bad Request Content-Type: application/json Cache-Control: no-store
  *
  * (Put the value of the "responseContent" parameter here.) </pre>
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
  *
  * <hr> <h3>{@code action} = {@link Action#CALLER_ERROR CALLER_ERROR}</h3>
  *
  * <p> The {@code action} value {@link Action#CALLER_ERROR CALLER_ERROR} means that the API call is
  * wrong. For example, the "{@code order}" request parameter is missing. </p>
  *
  * <p> Caller errors should be solved before the service is deployed in a production environment.
  * </p>
  * @param resultCode
  *   The result code of the API call.
  * @param resultMessage
  *   The result message of the API call.
  * @param action
  *   The next action that the implementation of the deferred credential endpoint should take.
  * @param responseContent
  *   The content of the response that the implementation of the deferred credential endpoint should
  *   return.
  */
final case class CredentialDeferredIssueResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[CredentialDeferredIssueResponse.CredentialDeferredIssueResponseAction],
    responseContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object CredentialDeferredIssueResponse {

  /**
    * The next action that the implementation of the deferred credential endpoint should take.
    */
  enum CredentialDeferredIssueResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    /**
      * A credential was issued successfully. The implementation of the deferred credential endpoint
      * should return a successful response with the HTTP status code "200 OK" and the content type
      * {@code application/json}.
      */
    case OK

    /**
      * A credential was issued successfully and the deferred credential response should be
      * encrypted. The implementation of the deferred credential endpoint should return a successful
      * response with the TTP status code "200 OK" and the content type {@code application/jwt}.
      *
      * @since 3.86
      */
    case OK_JWT

    /**
      * The original deferred credential request is wrong. This can happen, for example, when the
      * process for encrypting the deferred credential response with the encryption parameters
      * specified in the deferred credential request failed.
      *
      * @since 3.86
      */
    case BAD_REQUEST

    /**
      * The feature of Verifiable Credentials is not enabled in the service configuration.
      */
    case FORBIDDEN

    /**
      * An error occurred on Authlete side.
      */
    case INTERNAL_SERVER_ERROR

    /**
      * The API call is invalid.
      */
    case CALLER_ERROR

  }

}
