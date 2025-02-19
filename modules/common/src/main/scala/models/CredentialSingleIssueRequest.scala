package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * A request to Authlete's {@code /vci/single/issue} API.
  *
  * <p> The Authlete API is supposed to be called by the implementation of the <b>credential
  * endpoint</b>. The endpoint is defined in the "<a href=
  * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID for
  * Verifiable Credential Issuance</a>" (OID4VCI) specification. </p>
  *
  * <p> The implementation of the credential endpoint is expected to call the following Authlete
  * APIs in the order. </p>
  *
  * <ol> <li>{@code /auth/introspection} <li>{@code /vci/single/parse} <li>{@code /vci/single/issue}
  * </ol>
  *
  * <p> The role of the {@code /vci/single/issue} API is to issue a credential or a transaction ID
  * and to prepare a response that should be returned from the credential endpoint. </p>
  * @param accessToken
  *   The access token that was presented at the credential endpoint.
  * @param order
  *   The instruction for credential issuance.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */

final case class CredentialSingleIssueRequest(
    accessToken: Option[String],
    order: Option[CredentialIssuanceOrder]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
