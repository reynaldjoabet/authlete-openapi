package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Request to Authlete's {@code /vci/offer/create} API.
  *
  * <p> The API is used to create a <b>credential offer</b>. </p>
  *
  * <p> A credential offer is a JSON object that is used as the value of the
  * {@code credential_offer} parameter sent to the credential offer endpoint of the wallet or is
  * returned from an endpoint designated by the {@code credential_offer_uri} parameter. </p>
  *
  * <p> A credential offer looks like below. Properties in this class (= request parameters to the
  * {@code /vci/offer/create} API) are used to control the content of the credential offer being
  * created. </p>
  *
  * <blockquote> <pre> { "credential_issuer": "...", "credential_configuration_ids": [ ... ],
  * "grants": { "authorization_code": { "issuer_state": "..." },
  * "urn:ietf:params:oauth:grant-type:pre-authorized_code": { "pre-authorized_code": "...",
  * "tx_code": { "input_mode": "numeric", "length": 6, "description": "..." } } } } </pre>
  * </blockquote>
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
  * <p> Another breaking change. The {@code credential_configurations} property in a credential
  * offer has been renamed to {@code credential_configuration_ids}. (January, 2024) </p>
  * @param credentialConfigurationIds
  *   The value of the {@code credential_configuration_ids} array.
  * @param authorizationCodeGrantIncluded
  *   The flag to include the {@code authorization_code} object in the {@code grants} object.
  * @param issuerStateIncluded
  *   The flag to include the {@code issuer_state} property in the {@code authorization_code} object
  *   in the {@code grants} object.
  * @param preAuthorizedCodeGrantIncluded
  *   The flag to include the {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
  *   the {@code grants} object.
  * @param subject
  *   The subject associated with the credential offer.
  * @param duration
  *   The duration of the credential offer.
  * @param context
  *   A general-purpose arbitrary string.
  * @param properties
  *   Extra properties to associate with the credential offer.
  * @param jwtAtClaims
  *   Additional claims that are added to the payload part of the JWT access token.
  * @param authTime
  *   The time at which the user authentication was performed during the course of issuing the
  *   credential offer.
  * @param acr
  *   The Authentication Context Class Reference of the user authentication performed during the
  *   course of issuing the credential offer.
  * @param txCode
  *   The transaction code.
  * @param txCodeInputMode
  *   The input mode of the transaction code.
  * @param txCodeDescription
  *   The description of the transaction code.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialOfferCreateRequest(
    credentialConfigurationIds: Array[String],
    authorizationCodeGrantIncluded: Boolean,
    issuerStateIncluded: Boolean,
    preAuthorizedCodeGrantIncluded: Boolean,
    subject: String,
    duration: Long,
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
