package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's {@code /federation/configuration} API.
  *
  * <p> The Authlete API creates an <b>entity configuration</b>, which is defined in the "<a
  * href="https://openid.net/specs/openid-federation-1_0.html">OpenID Federation 1.0</a>"
  * specification. </p>
  *
  * <p> The optional "{@code entityTypes}" request parameter specifies the entity types for which
  * the entity configuration is created. For example, if the request parameter holds an array
  * containing "{@link EntityType#OPENID_PROVIDER OPENID_PROVIDER}" and "{@link
  * EntityType#OPENID_CREDENTIAL_ISSUER OPENID_CREDENTIAL_ISSUER}", the entity configuration will
  * contain metadata for both "{@code openid_provider}" and "{@code openid_credential_issuer}". To
  * be concrete, the "{@code metadata}" property in the entity configuration will look like the
  * following. </p>
  *
  * <blockquote> <pre> <span style="color: navy;">"metadata"</span>: { <span style="color:
  * navy;">"openid_provider"</span>: { ... }, <span style="color:
  * navy;">"openid_credential_issuer"</span>: { ... } } </pre> </blockquote>
  *
  * <p> Unsupported entity types in the "{@code entityTypes}" request parameter, to be specific,
  * other entity types than "{@code OPENID_PROVIDER}" and "{@code OPENID_CREDENTIAL_ISSUER}", are
  * ignored. </p>
  *
  * <p> If the feature of "Verifiable Credentials" (which is supported from Authlete 3.0) is not
  * enabled, "{@code OPENID_CREDENTIAL_ISSUER}" in the "{@code entityTypes}" request parameter is
  * ignored even if it is included. </p>
  *
  * <P> When the "{@code entityTypes}" request parameter is omitted or empty, or when the resultant
  * set of entity types becomes empty after unsupported entity types are dropped, the
  * {@code /federation/configuration} API will behave as if the "{@code entityTypes}" request
  * parameter were specified with "{@code OPENID_PROVIDER}" only. This behavior is for the backward
  * compatibility. </p>
  *
  * @see
  *   <a href="https://openid.net/specs/openid-federation-1_0.html" >OpenID Federation 1.0</a>
  *
  * @param entityTypes
  */

final case class FederationConfigurationRequest(
    entityTypes: Option[List[EntityType]]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
