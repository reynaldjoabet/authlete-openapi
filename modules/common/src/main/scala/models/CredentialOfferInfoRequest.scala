package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's {@code /vci/offer/info} API.
  *
  * <p> The API is used to get information about a <b>credential offer</b>. </p>
  * @param identifier
  *   The identifier of the credential offer.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialOfferInfoRequest(
    identifier: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
