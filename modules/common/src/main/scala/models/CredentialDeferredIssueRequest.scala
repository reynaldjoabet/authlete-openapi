package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * A request to Authlete's {@code /vci/deferred/issue} API.
  *
  * <p> The Authlete API is supposed to be called by the implementation of the <b>deferred
  * credential endpoint</b>. The endpoint is defined in the "<a href=
  * "https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID for
  * Verifiable Credential Issuance</a>" (OID4VCI) specification. </p>
  *
  * <p> The implementation of the deferred credential endpoint is expected to call the following
  * Authlete APIs in the order. </p>
  *
  * <ol> <li>{@code /auth/introspection} <li>{@code /vci/deferred/parse} <li>{@code
  * /vci/deferred/issue} </ol>
  *
  * <p> The role of the {@code /vci/deferred/issue} API is to issue a credential. </p>
  *
  * <p> If the credential for the transaction ID is not ready, the implementation of the deferred
  * credential endpoint should prepare an error response with {@code "error":"issuance_pending"}
  * manually and return it to the request sender, without calling the {@code /vci/deferred/issue}
  * API. </p>
  *
  * <pre> HTTP/1.1 400 Bad Request Content-Type: application/json Cache-Control: no-store
  *
  * { "error": "issuance_pending" } </pre>
  * @param order
  *   The instruction for credential issuance.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialDeferredIssueRequest(
    order: Option[CredentialIssuanceOrder]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
