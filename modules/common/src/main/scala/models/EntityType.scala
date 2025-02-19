package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.generic.semiauto.deriveDecoder
import io.circe.Codec
import io.circe.Decoder
import sttp.tapir.Schema

/**
  * Entity type identifiers in the context of the OpenID Federation 1&#x2E;0.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-federation-1_0.html#name-entity-type-identifiers"
  *   >OpenID Federation 1.0, 4.2. Entity Type Identifiers</a>
  *
  * @param value
  *   The integer representation of the entity type.
  * @param string
  *   The string representation of the entity type.
  */

enum EntityType(value: Short, string: String) derives Codec.AsObject {

  /**
    * {@code "openid_relying_party"} (1).
    *
    * @see
    *   <a
    *   href="https://openid.net/specs/openid-federation-1_0.html#name-openid-connect-relying-part"
    *   >OpenID Federation 1.0, 4.2.1. OpenID Connect Relying Party</a>
    */
  case OPENID_RELYING_PARTY extends EntityType(1, "openid_relying_party")

  /**
    * {@code "openid_provider"} (2).
    *
    * @see
    *   <a href="https://openid.net/specs/openid-federation-1_0.html#name-openid-provider" >OpenID
    *   Federation 1.0, 4.2.2. OpenID Provider</a>
    */
  case OPENID_PROVIDER extends EntityType(2, "openid_provider")

  /**
    * {@code "oauth_authorization_server"} (3).
    *
    * @see
    *   <a
    *   href="https://openid.net/specs/openid-federation-1_0.html#name-oauth-authorization-server"
    *   >OpenID Federation 1.0, 4.2.3. OAuth Authorization Server</a>
    */
  case OAUTH_AUTHORIZATION_SERVER extends EntityType(3, "oauth_authorization_server")

  /**
    * {@code "oauth_client"} (4).
    *
    * @see
    *   <a href="https://openid.net/specs/openid-federation-1_0.html#name-oauth-client" >OpenID
    *   Federation 1.0, 4.2.4. OAuth Client</a>
    */
  case OAUTH_CLIENT extends EntityType(4, "oauth_client")

  /**
    * {@code "oauth_resource"} (5).
    *
    * @see
    *   <a href="https://openid.net/specs/openid-federation-1_0.html#name-oauth-protected-resource"
    *   >OpenID Federation 1.0, 4.2.5. OAuth Protected Resource</a>
    */
  case OAUTH_RESOURCE extends EntityType(5, "oauth_resource")

  /**
    * {@code "federation_entity"} (6).
    *
    * @see
    *   <a href="https://openid.net/specs/openid-federation-1_0.html#name-federation-entity" >OpenID
    *   Federation 1.0, 4.7. Federation Entity</a>
    */
  case FEDERATION_ENTITY extends EntityType(6, "federation_entity")

  /**
    * {@code "openid_credential_issuer"} (7).
    *
    * <p> This entity type identifier represents a credential issuer that conforms to the "OpenID
    * for Verifiable Credential Issuance" specification. The specification is supported since
    * Authlete 3.0. </p>
    */
  case OPENID_CREDENTIAL_ISSUER extends EntityType(7, "openid_credential_issuer")

}

object EntityType {

  implicit val jsonCodec: JsonValueCodec[EntityType] = JsonCodecMaker.make

  implicit val schema: Schema[EntityType] = Schema.string
  // List[common.models.EntityType

  // implicit val codec: Codec.AsObject[EntityType] = Codec.AsObject.from(jsonCodec)

  implicit val optionJsonCodec: JsonValueCodec[Option[EntityType]] = JsonCodecMaker.make

  implicit val optionSchema: Schema[Option[EntityType]] = Schema.derived

  implicit val entityTypeDecoder: Decoder[List[EntityType]] = deriveDecoder

}
