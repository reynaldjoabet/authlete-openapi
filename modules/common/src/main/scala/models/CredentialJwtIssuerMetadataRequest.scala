package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's {@code /vci/jwtissuer} API.
  *
  * <p> The Authlete API is supposed to be called from within the implementation of the JWT VC
  * issuer metadata endpoint ({@code /.well-known/jwt-vc-issuer}) of the credential issuer. </p>
  *
  * <p> The API will generate JSON like below. </p>
  *
  * <blockquote> <pre> { "issuer": "{@link Service}.{@link Service#getCredentialIssuerMetadata()
  * getCredentialIssuerMetadata()}.{@link CredentialIssuerMetadata#getCredentialIssuer()
  * getCredentialIssuer()}", "jwks_uri": "{@link Service}.{@link Service#getCredentialJwksUri()
  * getCredentialJwksUri()}" } </pre> </blockquote>
  *
  * <p> Note that the JWT VC issuer metadata endpoint ({@code /.well-known/jwt-vc-issuer}) is
  * different from the credential issuer metadata endpoint ({@code
  * /.well-known/openid-credential-issuer}). </p>
  *
  * <p> NOTE: The well-known path has been changed from {@code /.well-known/jwt-issuer} to
  * {@code /.well-known/jwt-vc-issuer} by a breaking change of the SD-JWT VC specification. </p>
  * @param pretty
  *   The flag indicating whether the metadata is written in the pretty
  *
  * @see
  *   CredentialJwtIssuerMetadataResponse
  * @see
  *   <a href="https://datatracker.ietf.org/doc/draft-ietf-oauth-sd-jwt-vc/" >SD-JWT-based
  *   Verifiable Credentials (SD-JWT VC)</a>
  */
final case class CredentialJwtIssuerMetadataRequest(
    pretty: Boolean
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
