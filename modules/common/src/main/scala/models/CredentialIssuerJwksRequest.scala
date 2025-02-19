package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's {@code /vci/jwks} API.
  *
  * <p> The Authlete API can be used to implement an endpoint that returns the JWK Set document of
  * the credential issuer that contains public keys only. </p>
  * @param pretty
  *   The flag indicating whether the JWK Set document is written in the pretty
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialIssuerJwksRequest(
    pretty: Boolean
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
