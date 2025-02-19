package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /vci/offer/info} API.
  *
  * <p> The API is used to get information about a <b>credential offer</b>. </p>
  * @param resultCode
  *   The result code of the API call.
  * @param resultMessage
  *   The result message of the API call.
  * @param action
  *   The result of the {@code /vci/offer/info} API call.
  * @param info
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */

final case class CredentialOfferInfoResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[CredentialOfferInfoResponse.CredentialOfferInfoResponseAction],
    info: Option[CredentialOfferInfo]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object CredentialOfferInfoResponse {

  enum CredentialOfferInfoResponseAction(value: String) derives Schema, Codec.AsObject {

    /**
      * Information about the credential offer has been obtained successfully.
      */
    case OK extends CredentialOfferInfoResponseAction("OK")

    /**
      * The feature of Verifiable Credentials is not enabled in the service configuration.
      */
    case FORBIDDEN extends CredentialOfferInfoResponseAction("FORBIDDEN")

    /**
      * The credential offer specified by the identifier was not found.
      */
    case NOT_FOUND extends CredentialOfferInfoResponseAction("NOT_FOUND")

    /**
      * The API call was wrong. For example, the {@code identifier} request parameter was missing.
      */
    case CALLER_ERROR extends CredentialOfferInfoResponseAction("CALLER_ERROR")

    /**
      * An error occurred on Authlete side.
      */
    case AUTHLETE_ERROR extends CredentialOfferInfoResponseAction("AUTHLETE_ERROR")

  }

}
