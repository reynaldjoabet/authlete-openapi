package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

//import com.authlete.common.dto.Client
/**
  * @param number
  *   The sequential number of the client. The value of this property is assigned by Authlete.
  * @param serviceNumber
  *   The sequential number of the service of the client application. The value of this property is
  *   assigned by Authlete.
  * @param developer
  *   The developer of the client application.
  * @param clientName
  *   The name of the client application. This property corresponds to `client_name` in OpenID
  *   Connect Dynamic Client Registration 1.0, 2. Client Metadata.
  * @param clientNames
  *   Client names with language tags. If the client application has different names for different
  *   languages, this property can be used to register the names.
  * @param description
  *   The description about the client application.
  * @param descriptions
  *   Descriptions about the client application with language tags. If the client application has
  *   different descriptions for different languages, this property can be used to register the
  *   descriptions.
  * @param clientId
  *   The client ID. The value of this property is assigned by Authlete.
  * @param clientSecret
  *   The client secret. A random 512-bit value encoded by base64url (86 letters). The value of this
  *   property is assigned by Authlete.
  * @param clientIdAlias
  *   The alias of the client ID. Note that the client ID alias is recognized only when this
  *   client's `clientIdAliasEnabled` property is set to `true` AND the service's
  *   `clientIdAliasEnabled` property is also set to `true`.
  * @param clientIdAliasEnabled
  *   The flag to indicate whether the client ID alias is enabled or not.
  * @param clientType
  *   The type of the client.
  * @param applicationType
  *   The type of the application.
  * @param logoUri
  *   The URL pointing to the logo image of the client application.
  * @param logoUris
  *   Logo image URLs with language tags. If the client application has different logo images for
  *   different languages, this property can be used to register URLs of the images.
  * @param contacts
  *   An array of email addresses of people responsible for the client application.
  * @param tlsClientCertificateBoundAccessTokens
  *   The flag to indicate whether this client use TLS client certificate bound access tokens.
  * @param dynamicallyRegistered
  *   The flag to indicate whether this client has been registered dynamically.
  * @param softwareId
  *   The unique identifier string assigned by the client developer or software publisher used by
  *   registration endpoints to identify the client software to be dynamically registered.
  * @param softwareVersion
  *   The version identifier string for the client software identified by the software ID.
  * @param registrationAccessTokenHash
  *   The hash of the registration access token for this client.
  * @param createdAt
  *   The time at which this client was created. The value is represented as milliseconds since the
  *   UNIX epoch (1970-01-01).
  * @param modifiedAt
  *   The time at which this client was last modified. The value is represented as milliseconds
  *   since the UNIX epoch (1970-01-01).
  * @param grantTypes
  *   A string array of grant types which the client application declares that it will restrict
  *   itself to using.
  * @param responseTypes
  *   A string array of response types which the client application declares that it will restrict
  *   itself to using.
  * @param redirectUris
  *   Redirect URIs that the client application uses to receive a response from the authorization
  *   endpoint.
  * @param authorizationSignAlg
  *   The signing algorithm for authorization.
  * @param authorizationEncryptionAlg
  *   The encryption algorithm for authorization.
  * @param authorizationEncryptionEnc
  *   The encryption encoding for authorization.
  * @param tokenAuthMethod
  *   The token authentication method.
  * @param tokenAuthSignAlg
  *   The signing algorithm for token authentication.
  * @param selfSignedCertificateKeyId
  *   The key ID of a JWK containing a self-signed certificate of this client.
  * @param tlsClientAuthSubjectDn
  *   The string representation of the expected subject distinguished name of the certificate this
  *   client will use in mutual TLS authentication.
  * @param tlsClientAuthSanDns
  *   The string representation of the expected DNS subject alternative name of the certificate this
  *   client will use in mutual TLS authentication.
  * @param tlsClientAuthSanUri
  *   The string representation of the expected URI subject alternative name of the certificate this
  *   client will use in mutual TLS authentication.
  * @param tlsClientAuthSanIp
  *   The string representation of the expected IP address subject alternative name of the
  *   certificate this client will use in mutual TLS authentication.
  * @param tlsClientAuthSanEmail
  *   The string representation of the expected email address subject alternative name of the
  *   certificate this client will use in mutual TLS authentication.
  * @param parRequired
  *   The flag to indicate whether this client is required to use the pushed authorization request
  *   endpoint.
  * @param requestObjectRequired
  *   The flag to indicate whether authorization requests from this client are always required to
  *   utilize a request object by using either `request` or `request_uri` request parameter.
  * @param requestSignAlg
  *   The signing algorithm for request objects.
  * @param requestEncryptionAlg
  *   The encryption algorithm for request objects.
  * @param requestEncryptionEnc
  *   The encryption encoding for request objects.
  * @param requestUris
  *   An array of URLs each of which points to a request object.
  * @param defaultMaxAge
  *   The default maximum authentication age in seconds.
  * @param defaultAcrs
  *   The default ACRs (Authentication Context Class References).
  * @param idTokenSignAlg
  *   The signing algorithm for ID tokens.
  * @param idTokenEncryptionAlg
  *   The encryption algorithm for ID tokens.
  * @param idTokenEncryptionEnc
  *   The encryption encoding for ID tokens.
  * @param authTimeRequired
  *   The flag to indicate whether this client requires `auth_time` claim to be embedded in the ID
  *   token.
  * @param subjectType
  *   The subject type.
  * @param sectorIdentifierUri
  *   The value of the sector identifier URI.
  * @param derivedSectorIdentifier
  *   The sector identifier host component as derived from either the `sector_identifier_uri` or the
  *   registered redirect URI.
  * @param jwksUri
  *   The URL pointing to the JWK Set of the client application.
  * @param jwks
  *   The content of the JWK Set of the client application.
  * @param userInfoSignAlg
  *   The signing algorithm for user info.
  * @param userInfoEncryptionAlg
  *   The encryption algorithm for user info.
  * @param userInfoEncryptionEnc
  *   The encryption encoding for user info.
  * @param loginUri
  *   The URL which a third party can use to initiate a login by the client application.
  * @param tosUri
  *   The URL pointing to the "Terms Of Service" page.
  * @param tosUris
  *   URLs of "Terms Of Service" pages with language tags.
  * @param policyUri
  *   The URL pointing to the page which describes the policy as to how end-user's profile data is
  *   used.
  * @param policyUris
  *   URLs of policy pages with language tags.
  * @param clientUri
  *   The URL pointing to the home page of the client application.
  * @param clientUris
  *   Home page URLs with language tags.
  * @param bcDeliveryMode
  *   The backchannel token delivery mode.
  * @param bcNotificationEndpoint
  *   The backchannel client notification endpoint.
  * @param bcRequestSignAlg
  *   The signing algorithm for backchannel requests.
  * @param bcUserCodeRequired
  *   The boolean flag to indicate whether a user code is required when this client makes a
  *   backchannel authentication request.
  * @param attributes
  *   The attributes of this client.
  * @param extension
  *   The client extension.
  * @param authorizationDetailsTypes
  *   The authorization details types that this client may use as values of the `type` field in
  *   `authorization_details`.
  * @param customMetadata
  *   The custom client metadata in JSON format.
  * @param frontChannelRequestObjectEncryptionRequired
  *   The flag indicating whether encryption of request object is required when the request object
  *   is passed through the front channel.
  * @param requestObjectEncryptionAlgMatchRequired
  *   The flag indicating whether the JWE alg of encrypted request object must match the
  *   `request_object_encryption_alg` client metadata.
  * @param requestObjectEncryptionEncMatchRequired
  *   The flag indicating whether the JWE enc of encrypted request object must match the
  *   `request_object_encryption_enc` client metadata.
  * @param digestAlgorithm
  *   The digest algorithm that this client requests the server to use when it computes digest
  *   values of external attachments.
  * @param singleAccessTokenPerSubject
  *   If `Enabled` is selected, an attempt to issue a new access token invalidates existing access
  *   tokens that are associated with the same combination of subject and client.
  * @param pkceRequired
  *   The flag to indicate whether the use of Proof Key for Code Exchange (PKCE) is always required
  *   for authorization requests by Authorization Code Flow.
  * @param pkceS256Required
  *   The flag to indicate whether `S256` is always required as the code challenge method whenever
  *   PKCE is used.
  * @param rsRequestSigned
  *   The flag to indicate whether the client is expected to sign requests to the resource server.
  * @param dpopRequired
  *   If the DPoP is required for this client.
  * @param automaticallyRegistered
  *   The flag indicating whether this client was registered by the "automatic" client registration
  *   of OIDC Federation.
  * @param explicitlyRegistered
  *   The flag indicating whether this client was registered by the "explicit" client registration
  *   of OIDC Federation.
  * @param rsSignedRequestKeyId
  *   The key ID of a JWK containing the public key used by this client to sign requests to the
  *   resource server.
  * @param clientRegistrationTypes
  *   The client registration types that the client has declared it may use.
  * @param organizationName
  *   The human-readable name representing the organization that manages this client.
  * @param signedJwksUri
  *   The URI of the endpoint that returns this client's JWK Set document in the JWT format.
  * @param entityId
  *   The entity ID of this client.
  * @param trustAnchorId
  *   The entity ID of the trust anchor of the trust chain that was used when this client was
  *   registered or updated by the mechanism defined in OpenID Connect Federation 1.0.
  * @param trustChain
  *   The trust chain that was used when this client was registered or updated by the mechanism
  *   defined in OpenID Connect Federation 1.0.
  * @param trustChainExpiresAt
  *   The time at which the trust chain expires. The value is represented as milliseconds since the
  *   UNIX epoch (1970-01-01).
  * @param trustChainUpdatedAt
  *   The time at which the trust chain was updated by the mechanism defined in OpenID Connect
  *   Federation 1.0.
  */
case class Client(
    applicationType: Option[ApplicationType] = None,
    authTimeRequired: Option[Boolean] = None,
    authorizationDetailsTypes: Option[Seq[String]] = None,
    authorizationEncryptionAlg: Option[JweAlg] = None,
    authorizationEncryptionEnc: Option[JweEnc] = None,
    authorizationSignAlg: Option[JwsAlg] = None,
    automaticallyRegistered: Option[Boolean] = None,
    attributes: Option[Seq[Pair]] = None,
    bcDeliveryMode: Option[String] = None,
    bcNotificationEndpoint: Option[String] = None,
    bcRequestSignAlg: Option[JwsAlg] = None,
    bcUserCodeRequired: Option[Boolean] = None,
    clientId: Option[Long] = None,
    clientIdAlias: Option[String] = None,
    clientIdAliasEnabled: Option[Boolean] = None,
    clientName: Option[String] = None,
    clientNames: Option[Seq[TaggedValue]] = None,
    clientRegistrationTypes: Option[Seq[ClientRegistrationType]] = None,
    clientSecret: Option[String] = None,
    clientType: Option[ClientType] = None,
    clientUri: Option[String] = None,
    clientUris: Option[Seq[TaggedValue]] = None,
    contacts: Option[Seq[String]] = None,
    createdAt: Option[Long] = None,
    customMetadata: Option[String] = None,
    defaultAcrs: Option[Seq[String]] = None,
    defaultMaxAge: Option[Long] = None,
    description: Option[String] = None,
    descriptions: Option[Seq[TaggedValue]] = None,
    digestAlgorithm: Option[String] = None,
    display: Option[Display] = None,
    dpopRequired: Option[Boolean] = None,
    dynamicallyRegistered: Option[Boolean] = None,
    entityId: Option[String] = None,
    explicitlyRegistered: Option[Boolean] = None,
    extension: Option[ClientExtension] = None,
    frontChannelRequestObjectEncryptionRequired: Option[Boolean] = None,
    grantTypes: Option[Seq[GrantType]] = None,
    idTokenEncryptionAlg: Option[JweAlg] = None,
    idTokenEncryptionEnc: Option[JweEnc] = None,
    idTokenSignAlg: Option[JwsAlg] = None,
    jwks: Option[String] = None,
    jwksUri: Option[String] = None,
    loginUri: Option[String] = None,
    logoUri: Option[String] = None,
    logoUris: Option[Seq[TaggedValue]] = None,
    modifiedAt: Option[Long] = None,
    number: Option[Long] = None,
    organizationName: Option[String] = None,
    parRequired: Option[Boolean] = None,
    pkceRequired: Option[Boolean] = None,
    pkceS256Required: Option[Boolean] = None,
    policyUri: Option[String] = None,
    policyUris: Option[Seq[TaggedValue]] = None,
    redirectUris: Option[Seq[String]] = None,
    registrationAccessTokenHash: Option[String] = None,
    requestEncryptionAlg: Option[JweAlg] = None,
    requestEncryptionEnc: Option[JweEnc] = None,
    requestObjectEncryptionAlgMatchRequired: Option[Boolean] = None,
    requestObjectEncryptionEncMatchRequired: Option[Boolean] = None,
    requestObjectRequired: Option[Boolean] = None,
    requestSignAlg: Option[JwsAlg] = None,
    requestUris: Option[Seq[String]] = None,
    responseTypes: Option[Seq[ResponseType]] = None,
    rsRequestSigned: Option[Boolean] = None,
    rsSignedRequestKeyId: Option[String] = None,
    sectorIdentifierUri: Option[String] = None,
    selfSignedCertificateKeyId: Option[String] = None,
    serviceNumber: Option[Long] = None,
    signedJwksUri: Option[String] = None,
    singleAccessTokenPerSubject: Option[Boolean] = None,
    softwareId: Option[String] = None,
    softwareVersion: Option[String] = None,
    subjectType: Option[SubjectType] = None,
    tlsClientAuthSanDns: Option[String] = None,
    tlsClientAuthSanEmail: Option[String] = None,
    tlsClientAuthSanIp: Option[String] = None,
    tlsClientAuthSanUri: Option[String] = None,
    tlsClientAuthSubjectDn: Option[String] = None,
    tlsClientCertificateBoundAccessTokens: Option[Boolean] = None,
    tokenAuthMethod: Option[ClientAuthMethod] = None,
    tokenAuthSignAlg: Option[JwsAlg] = None,
    tosUri: Option[String] = None,
    tosUris: Option[Seq[TaggedValue]] = None,
    trustAnchorId: Option[String] = None,
    trustChain: Option[Seq[String]] = None,
    trustChainExpiresAt: Option[Long] = None,
    trustChainUpdatedAt: Option[Long] = None,
    userInfoEncryptionAlg: Option[JweAlg] = None,
    userInfoEncryptionEnc: Option[JweEnc] = None,
    userInfoSignAlg: Option[JwsAlg] = None
) derives Schema,
      Codec.AsObject

object Client {

  implicit val codec: JsonValueCodec[Client] =
    JsonCodecMaker.make(codecMakerConfig)

  implicit val optionClientCodec: JsonValueCodec[Option[Client]] =
    JsonCodecMaker.make(codecMakerConfig)

}
