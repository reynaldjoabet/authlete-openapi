package common.models

import com.authlete.common.dto.CredentialBatchIssueRequest
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * A request to Authlete's {@code /vci/batch/issue} API.
  *
  * <p> The Authlete API is supposed to be called by the implementation of the <b>batch credential
  * endpoint</b>. The endpoint is defined in the "<a href=
  * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID for
  * Verifiable Credential Issuance</a>" (OID4VCI) specification. </p>
  *
  * <p> The implementation of the batch credential endpoint is expected to call the following
  * Authlete APIs in the order. </p>
  *
  * <ol> <li>{@code /auth/introspection} <li>{@code /vci/batch/parse} <li>{@code /vci/batch/issue}
  * </ol>
  *
  * <p> The role of the {@code /vci/batch/issue} API is to issue credentials and/or transaction IDs
  * and to prepare a response that should be returned from th batch credential endpoint. </p>
  * @param accessToken
  *   The access token that was presented at the batch credential endpoint.
  * @param orders
  *   The instructions for issuance of credentials and/or transaction IDs.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */

final case class CredentialBatchIssueRequest(
    accessToken: String,
    orders: Array[CredentialIssuanceOrder]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
