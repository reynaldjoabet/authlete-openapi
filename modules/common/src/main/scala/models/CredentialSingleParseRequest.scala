package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to the {@code /vci/single/parse} API.
  *
  * <p> The Authlete API is supposed to be used to parse a credential request that the credential
  * endpoint received. </p>
  *
  * <p> Note that the implementation of the credential endpoint should call the
  * {@code /auth/introspection} API to check whether the access token is valid BEFORE calling the
  * {@code /vci/single/parse} API. The validation on the access token by the
  * {@code /vci/single/parse} API is limited and not exhaustive. For example, the
  * {@code /vci/single/parse} API does not check certificate binding (<a
  * href="https://www.rfc-editor.org/rfc/rfc8705.html" >RFC 8705</a>). </p>
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  *
  * @param accessToken
  *   The access token that came along with the credential request.
  *
  * @param requestContent
  *
  * The message body of the credential request. The expected format is
  */
final case class CredentialSingleParseRequest(
    accessToken: Option[String],
    requestContent: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
