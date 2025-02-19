package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to the {@code /vci/batch/parse} API.
  *
  * <p> The Authlete API is supposed to be used to parse a batch credential request that the batch
  * credential endpoint received. </p>
  *
  * <p> Note that the implementation of the batch credential endpoint should call the
  * {@code /auth/introspection} API to check whether the access token is valid BEFORE calling the
  * {@code /vci/batch/parse} API. The validation on the access token by the {@code /vci/batch/parse}
  * API is limited and not exhaustive. For example, the {@code /vci/batch/parse} API does not check
  * certificate binding (<a href="https://www.rfc-editor.org/rfc/rfc8705.html" >RFC 8705</a>). </p>
  * @param accessToken
  *   The access token that was presented at the batch credential endpoint.
  * @param requestContent
  *   The message body of the batch credential request.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */

final case class CredentialBatchParseRequest(
    accessToken: String,
    requestContent: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
