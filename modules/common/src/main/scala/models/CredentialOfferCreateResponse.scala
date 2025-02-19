package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /vci/offer/create} API.
  *
  * <p> The API is used to create a <b>credential offer</b>. </p>
  * @param resultCode
  *   The result code of the API call.
  * @param resultMessage
  *   The result message of the API call.
  * @param action
  *   The result of the {@code /vci/offer/create} API call.
  * @param info
  *   Information about the credential offer.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialOfferCreateResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[CredentialOfferCreateResponse.CredentialOfferCreateResponseAction],
    info: Option[CredentialOfferInfo]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object CredentialOfferCreateResponse {

  enum CredentialOfferCreateResponseAction(value: String) derives Schema, Codec.AsObject {

    /**
      * The {@code /vci/offer/create} API has created a credential offer successfully.
      */
    case CREATED extends CredentialOfferCreateResponseAction("CREATED")

    /**
      * The feature of Verifiable Credentials is not enabled in the service configuration.
      */
    case FORBIDDEN extends CredentialOfferCreateResponseAction("FORBIDDEN")

    /**
      * The API call was wrong. For example, the {@code subject} request parameter was missing.
      */
    case CALLER_ERROR extends CredentialOfferCreateResponseAction("CALLER_ERROR")

    /**
      * An error occurred on Authlete side.
      */
    case AUTHLETE_ERROR extends CredentialOfferCreateResponseAction("AUTHLETE_ERROR")

  }

}

import com.authlete.common.dto.CredentialOfferCreateResponse
