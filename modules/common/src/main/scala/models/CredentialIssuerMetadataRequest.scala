package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's {@code /vci/metadata} API.
  *
  * <p> The Authlete API is supposed to be used from within the implementation of the credential
  * issuer metadata endpoint ({@code /.well-known/openid-credential-issuer}). </p>
  * @param pretty
  *   The flag indicating whether the metadata is written in the pretty
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialIssuerMetadataRequest(
    pretty: Boolean
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
