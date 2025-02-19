package common.models

import java.net.URI

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Information about a credential offer.
  *
  * <h3>Breaking Changes</h3>
  *
  * <p> The "OpenID for Verifiable Credential Issuance" specification tends to repeat breaking
  * changes. Such changes affect this Java class. </p>
  *
  * <p> In the past draft of the specification, elements in the "{@code credentials}" array in a
  * credential offer are either strings or JSON objects. Therefore, the type of the "{@code
  * credentials}" property in the previous implementation of this class was a "string" whose content
  * must be able to be parsed as a JSON array. However, as a result of a breaking change in the
  * specification, it is ensured that all elements in the "{@code credentials}" array in a
  * credential offer are strings. To make it easier to treat the "{@code credentials}" property of
  * this class, the type of the property has been changed from a string to an array of strings. </p>
  *
  * <p> Due to another breaking change made in December 2023, the {@code credentials} property in a
  * credential offer has been renamed to {@code credential_configurations}. In addition, the
  * {@code user_pin_required} boolean property has been replaced with the {@code tx_code} JSON
  * object. </p>
  *
  * <p> Another breaking change. The {@code credential_configurations} property has been renamed to
  * {@code credential_configuration_ids}. </p>
  * @param identifier
  *   The identifier of the credential offer. <p> The identifier is a base64url string with 256-bit
  *   entropy consisting of 43 characters. </p>
  * @param credentialOffer
  *   The credential offer in the JSON format. <p> The value is suitable for use as the value of the
  *   {@code credential_offer} parameter which is sent to the credential offer endpoint of the
  *   wallet. It is also suitable as the message body of a response returned from the endpoint
  *   designated by the {@code credential_offer_uri} parameter. </p> <p> The credential offer holds
  *   JSON like below. </p> <blockquote> <pre> { "credential_issuer": "...",
  *   "credential_configuration_ids": [ ... ],
  *
  * "grants": { "authorization_code": { "issuer_state": "..." },
  * "urn:ietf:params:oauth:grant-type:pre-authorized_code": { "pre-authorized_code": "...",
  * "tx_code": { "input_mode": "numeric", "length": 6, "description": "..." } } } } </pre>
  * </blockquote>
  * @param credentialIssuer
  *   The identifier of the credential issuer.
  * @since 3.60
  * @param credentialConfigurationIds
  *   The value of the {@code credential_configuration_ids} array.
  * @since 3.93
  * @param authorizationCodeGrantIncluded
  *   The flag indicating whether the {@code authorization_code} object is included in the
  *   {@code grants} object.
  * @param issuerStateIncluded
  *   The flag indicating whether the {@code issuer_state} property is included in the
  *   {@code authorization_code} object in the {@code grants} object.
  * @param issuerState
  *   The value of the {@code issuer_state} property in the {@code authorization_code} object in the
  *   {@code grants} object.
  * @param preAuthorizedCodeGrantIncluded
  *   The flag indicating whether the {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
  *   object is included in the {@code grants} object.
  * @param preAuthorizedCode
  *   The value of the {@code pre-authorized_code} property in the
  *   {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in the {@code grants}
  *   object.
  * @param subject
  *   The subject associated with the credential offer.
  * @param expiresAt
  *   The time at which the credential offer will expire.
  * @param context
  *   The general-purpose arbitrary string.
  * @param properties
  *   Extra properties to associate with the credential offer.
  * @since 3.62
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access token.
  * @since 3.62
  * @param authTime
  *   The time at which the user authentication was performed during the course of issuing the
  *   credential offer.
  * @since 3.62
  * @param acr
  *   The Authentication Context Class Reference of the user authentication performed during the
  *   course of issuing the credential offer.
  * @since 3.62
  * @param txCode
  *   The transaction code.
  * @since 3.91
  * @param txCodeInputMode
  *   The input mode of the transaction code.
  * @since 3.91
  * @param txCodeDescription
  *   The description of the transaction code.
  * @since 3.91
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialOfferInfo(
    identifier: String,
    credentialOffer: String,
    credentialIssuer: String, // URI
    credentialConfigurationIds: Array[String],
    authorizationCodeGrantIncluded: Boolean,
    issuerStateIncluded: Boolean,
    issuerState: String,
    preAuthorizedCodeGrantIncluded: Boolean,
    preAuthorizedCode: String,
    subject: String,
    expiresAt: Long,
    context: String,
    properties: Array[Property],
    jwtAtClaims: String,
    authTime: Long,
    acr: String,
    txCode: String,
    txCodeInputMode: String,
    txCodeDescription: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
