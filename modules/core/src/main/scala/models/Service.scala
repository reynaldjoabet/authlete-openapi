package models

import io.circe.Codec

/** @param number
  *   The sequential number of the service. The value of this property is
  *   assigned by Authlete.
  * @param serviceOwnerNumber
  *   The sequential number of the service owner. The value of this property is
  *   assigned by Authlete.
  * @param serviceName
  *   The name of the service.
  * @param issuer
  *   The issuer identifier of the service.
  * @param description
  *   The description of the service.
  * @param apiKey
  *   The API key of the service.
  * @param apiSecret
  *   The API secret of the service.
  * @param clientsPerDeveloper
  *   The maximum number of clients that a developer can have under the service.
  * @param clientIdAliasEnabled
  *   The flag indicating whether the service supports the alias for client ID.
  * @param metadata
  *   The metadata of the service.
  * @param createdAt
  *   The time at which the service was created.
  * @param modifiedAt
  *   The time at which the service was last modified.
  * @param authenticationCallbackEndpoint
  *   The endpoint that the service implementation provides for the
  *   authentication callback.
  * @param authenticationCallbackApiKey
  *   The API key for the authentication callback endpoint.
  * @param authenticationCallbackApiSecret
  *   The API secret for the authentication callback endpoint.
  * @param supportedSnses
  *   The list of supported SNSes (Social Networking Services).
  * @param snsCredentials
  *   The credentials for SNSes.
  * @param supportedAcrs
  *   The list of supported ACRs (Authentication Context Class References).
  * @param developerAuthenticationCallbackEndpoint
  *   The endpoint that the service implementation provides for the developer
  *   authentication callback.
  * @param developerAuthenticationCallbackApiKey
  *   The API key for the developer authentication callback endpoint.
  * @param developerAuthenticationCallbackApiSecret
  *   The API secret for the developer authentication callback endpoint.
  * @param supportedDeveloperSnses
  *   The list of supported SNSes (Social Networking Services) for developers.
  * @param developerSnsCredentials
  *   The credentials for SNSes for developers.
  * @param supportedGrantTypes
  *   The list of supported grant types.
  * @param supportedResponseTypes
  *   The list of supported response types.
  * @param supportedAuthorizationDetailsTypes
  *   The list of supported authorization details types.
  * @param supportedServiceProfiles
  *   The list of supported service profiles.
  * @param errorDescriptionOmitted
  *   The flag indicating whether the authorization server implementation omits
  *   the `error_description` parameter from error responses.
  * @param errorUriOmitted
  *   The flag indicating whether the authorization server implementation omits
  *   the `error_uri` parameter from error responses.
  * @param authorizationEndpoint
  *   The URL of the authorization endpoint.
  * @param directAuthorizationEndpointEnabled
  *   The flag indicating whether the authorization endpoint is enabled.
  * @param supportedUiLocales
  *   The list of supported UI locales.
  * @param supportedDisplays
  *   The list of supported displays.
  * @param pkceRequired
  *   The flag indicating whether the PKCE (Proof Key for Code Exchange) is
  *   required.
  * @param pkceS256Required
  *   The flag indicating whether the PKCE (Proof Key for Code Exchange) with
  *   S256 is required.
  * @param authorizationResponseDuration
  *   The duration of the authorization response.
  * @param tokenEndpoint
  *   The URL of the token endpoint.
  * @param directTokenEndpointEnabled
  *   The flag indicating whether the token endpoint is enabled.
  * @param supportedTokenAuthMethods
  *   The list of supported token authentication methods.
  * @param missingClientIdAllowed
  *   The flag indicating whether the authorization server implementation allows
  *   clients to omit the `client_id` request parameter.
  * @param revocationEndpoint
  *   The URL of the revocation endpoint.
  * @param directRevocationEndpointEnabled
  *   The flag indicating whether the revocation endpoint is enabled.
  * @param supportedRevocationAuthMethods
  *   The list of supported revocation authentication methods.
  * @param introspectionEndpoint
  *   The URL of the introspection endpoint.
  * @param directIntrospectionEndpointEnabled
  *   The flag indicating whether the introspection endpoint is enabled.
  * @param supportedIntrospectionAuthMethods
  *   The list of supported introspection authentication methods.
  * @param pushedAuthReqEndpoint
  *   The URL of the pushed authorization request endpoint.
  * @param pushedAuthReqDuration
  *   The duration of the pushed authorization request.
  * @param parRequired
  *   The flag indicating whether the `par` (Pushed Authorization Request)
  *   endpoint is required.
  * @param requestObjectRequired
  *   The flag indicating whether the request object is always required.
  * @param traditionalRequestObjectProcessingApplied
  *   The flag indicating whether the traditional request object processing is
  *   applied.
  * @param mutualTlsValidatePkiCertChain
  *   The flag indicating whether the PKI-based certificate chain validation is
  *   applied to the client certificate in the MTLS (Mutual TLS) authentication.
  * @param trustedRootCertificates
  *   The list of trusted root certificates.
  * @param mtlsEndpointAliases
  *   The list of aliases for MTLS (Mutual TLS) endpoints.
  * @param accessTokenType
  *   The type of access token.
  * @param tlsClientCertificateBoundAccessTokens
  *   The flag indicating whether the access tokens are bound to the client
  *   certificate.
  * @param accessTokenDuration
  *   The duration of access tokens.
  * @param singleAccessTokenPerSubject
  *   The flag indicating whether only one access token per subject is issued.
  * @param accessTokenSignAlg
  *   The algorithm used for signing access tokens.
  * @param accessTokenSignatureKeyId
  *   The key ID of the key used for signing access tokens.
  * @param refreshTokenDuration
  *   The duration of refresh tokens.
  * @param refreshTokenDurationKept
  *   The flag indicating whether the duration of refresh tokens is kept.
  * @param refreshTokenDurationReset
  *   The flag indicating whether the duration of refresh tokens is reset.
  * @param refreshTokenKept
  *   The flag indicating whether refresh tokens are kept.
  * @param supportedScopes
  *   The list of supported scopes.
  * @param scopeRequired
  *   The flag indicating whether the `scope` request parameter is required.
  * @param idTokenDuration
  *   The duration of ID tokens.
  * @param allowableClockSkew
  *   The allowable clock skew.
  * @param supportedClaimTypes
  *   The list of supported claim types.
  * @param supportedClaimLocales
  *   The list of supported claim locales.
  * @param supportedClaims
  *   The list of supported claims.
  * @param claimShortcutRestrictive
  *   The flag indicating whether the claim shortcut is restrictive.
  * @param jwksUri
  *   The URL of the JWKS (JSON Web Key Set) document.
  * @param directJwksEndpointEnabled
  *   The flag indicating whether the JWKS (JSON Web Key Set) endpoint is
  *   enabled.
  * @param jwks
  *   The JWKS (JSON Web Key Set) document.
  * @param idTokenSignatureKeyId
  *   The key ID of the key used for signing ID tokens.
  * @param userInfoSignatureKeyId
  *   The key ID of the key used for signing userinfo responses.
  * @param authorizationSignatureKeyId
  *   The key ID of the key used for signing authorization responses.
  * @param userInfoEndpoint
  *   The URL of the userinfo endpoint.
  * @param directUserInfoEndpointEnabled
  *   The flag indicating whether the userinfo endpoint is enabled.
  * @param dynamicRegistrationSupported
  *   The flag indicating whether dynamic client registration is supported.
  * @param registrationEndpoint
  *   The URL of the registration endpoint.
  * @param registrationManagementEndpoint
  *   The URL of the registration management endpoint.
  * @param policyUri
  *   The URL of the policy document.
  * @param tosUri
  *   The URL of the terms of service document.
  * @param serviceDocumentation
  *   The URL of the service documentation.
  * @param backchannelAuthenticationEndpoint
  *   The URL of the backchannel authentication endpoint.
  * @param supportedBackchannelTokenDeliveryModes
  *   The list of supported backchannel token delivery modes.
  * @param backchannelAuthReqIdDuration
  *   The duration of the backchannel authentication request ID.
  * @param backchannelPollingInterval
  *   The polling interval of the backchannel authentication endpoint.
  * @param backchannelUserCodeParameterSupported
  *   The flag indicating whether the `user_code` request parameter is
  *   supported.
  * @param backchannelBindingMessageRequiredInFapi
  *   The flag indicating whether the binding message is required in the FAPI
  *   (Financial-grade API) mode.
  * @param deviceAuthorizationEndpoint
  *   The URL of the device authorization endpoint.
  * @param deviceVerificationUri
  *   The URL of the device verification URI.
  * @param deviceVerificationUriComplete
  *   The URL of the device verification URI complete.
  * @param deviceFlowCodeDuration
  *   The duration of the device flow code.
  * @param deviceFlowPollingInterval
  *   The polling interval of the device flow.
  * @param userCodeCharset
  *   The charset of the `user_code` request parameter.
  * @param userCodeLength
  *   The length of the `user_code` request parameter.
  * @param supportedTrustFrameworks
  *   The list of supported trust frameworks.
  * @param supportedEvidence
  *   The list of supported evidence.
  * @param supportedIdentityDocuments
  *   The list of supported identity documents.
  * @param supportedVerificationMethods
  *   The list of supported verification methods.
  * @param supportedVerifiedClaims
  *   The list of supported verified claims.
  * @param verifiedClaimsValidationSchemaSet
  *   The set of verified claims validation schema.
  * @param attributes
  *   The attributes of the service.
  * @param nbfOptional
  *   The flag indicating whether the `nbf` claim is optional.
  * @param issSuppressed
  *   The flag indicating whether the `iss` claim is suppressed.
  * @param supportedCustomClientMetadata
  *   The list of supported custom client metadata.
  * @param tokenExpirationLinked
  *   The flag indicating whether the expiration date of access tokens is linked
  *   to that of refresh tokens.
  * @param frontChannelRequestObjectEncryptionRequired
  *   The flag indicating whether front-channel request object encryption is
  *   required.
  * @param requestObjectEncryptionAlgMatchRequired
  *   The flag indicating whether the algorithm of request object encryption
  *   must match the algorithm of request object encryption.
  * @param requestObjectEncryptionEncMatchRequired
  *   The flag indicating whether the encryption method of request object
  *   encryption must match the encryption method of request object encryption.
  * @param hsmEnabled
  *   The flag indicating whether HSM (Hardware Security Module) support is
  *   enabled for this service. When this flag is `false`, keys managed in HSMs
  *   are not used even if they exist. In addition, `/api/hsk/_*` APIs reject
  *   all requests. Even if this flag is `true`, HSM-related features do not
  *   work if the configuration of the Authlete server you are using does not
  *   support HSM.
  * @param hsks
  *   The information about keys managed on HSMs (Hardware Security Modules).
  *   This `hsks` property is output only, meaning that `hsks` in requests to
  *   `/api/service/create` API and `/api/service/update` API do not have any
  *   effect. The contents of this property is controlled only by `/api/hsk/_*`
  *   APIs.
  * @param grantManagementEndpoint
  *   The URL of the grant management endpoint.
  * @param grantManagementActionRequired
  *   The flag indicating whether every authorization request (and any request
  *   serving as an authorization request such as CIBA backchannel
  *   authentication request and device authorization request) must include the
  *   `grant_management_action` request parameter. This property corresponds to
  *   the `grant_management_action_required` server metadata defined in [Grant
  *   Management for OAuth
  *   2.0](https://openid.net/specs/fapi-grant-management.html). Note that
  *   setting true to this property will result in blocking all public clients
  *   because the specification requires that grant management be usable only by
  *   confidential clients for security reasons.
  * @param unauthorizedOnClientConfigSupported
  *   The flag indicating whether Authlete\'s `/api/client/registration` API
  *   uses `UNAUTHORIZED` as a value of the `action` response parameter when
  *   appropriate. The `UNAUTHORIZED` enum value was initially not defined as a
  *   possible value of the `action` parameter in an `/api/client/registration`
  *   API response. This means that implementations of client `configuration`
  *   endpoint were not able to conform to [RFC
  *   7592](https://www.rfc-editor.org/rfc/rfc7592.html) strictly. For backward
  *   compatibility (to avoid breaking running systems), Authlete\'s
  *   `/api/client/registration` API does not return the `UNAUTHORIZED` enum
  *   value if this flag is not turned on. The steps an existing implementation
  *   of client configuration endpoint has to do in order to conform to the
  *   requirement related to \"401 Unauthorized\" are as follows. 1. Update the
  *   Authlete library (e.g. authlete-java-common) your system is using. 2.
  *   Update your implementation of client configuration endpoint so that it can
  *   handle the `UNAUTHORIZED` action. 3. Turn on this
  *   `unauthorizedOnClientConfigSupported` flag.
  * @param dcrScopeUsedAsRequestable
  *   The flag indicating whether the `scope` request parameter in dynamic
  *   client registration and update requests (RFC 7591 and RFC 7592) is used as
  *   scopes that the client can request. Limiting the range of scopes that a
  *   client can request is achieved by listing scopes in the
  *   `client.extension.requestableScopes` property and setting the
  *   `client.extension.requestableScopesEnabled` property to `true`. This
  *   feature is called \"requestable scopes\". This property affects behaviors
  *   of `/api/client/registration` and other family APIs.
  * @param endSessionEndpoint
  *   The endpoint for clients ending the sessions. A URL that starts with
  *   `https://` and has no fragment component. For example,
  *   `https://example.com/auth/endSession`. The value of this property is used
  *   as `end_session_endpoint` property in the [OpenID Provider
  *   Metadata](https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata).
  * @param loopbackRedirectionUriVariable
  *   The flag indicating whether the port number component of redirection URIs
  *   can be variable when the host component indicates loopback. When this flag
  *   is `true`, if the host component of a redirection URI specified in an
  *   authorization request indicates loopback (to be precise, when the host
  *   component is localhost, `
  * @param requestObjectAudienceChecked
  *   The flag indicating whether Authlete checks whether the `aud` claim of
  *   request objects matches the issuer identifier of this service. [Section
  *   6.1. Passing a Request Object by
  *   Value](https://openid.net/specs/openid-connect-core-1_0.html#JWTRequests)
  *   of [OpenID Connect Core
  *   1.0](https://openid.net/specs/openid-connect-core-1_0.html) has the
  *   following statement. > The `aud` value SHOULD be or include the OP\'s
  *   Issuer Identifier URL. Likewise, [Section 4. Request
  *   Object](https://www.rfc-editor.org/rfc/rfc9101.html#section-4) of [RFC
  *   9101](https://www.rfc-editor.org/rfc/rfc9101.html) (The OAuth 2.0
  *   Authorization Framework: JWT-Secured Authorization Request (JAR)) has the
  *   following statement. > The value of aud should be the value of the
  *   authorization server (AS) issuer, as defined in [RFC
  *   8414](https://www.rfc-editor.org/rfc/rfc8414.html). As excerpted above,
  *   validation on the `aud` claim of request objects is optional. However, if
  *   this flag is turned on, Authlete checks whether the `aud` claim of request
  *   objects matches the issuer identifier of this service and raises an error
  *   if they are different.
  * @param accessTokenForExternalAttachmentEmbedded
  *   The flag indicating whether Authlete generates access tokens for external
  *   attachments and embeds them in ID tokens and userinfo responses.
  * @param authorityHints
  *   Identifiers of entities that can issue entity statements for this service.
  *   This property corresponds to the `authority_hints` property that appears
  *   in a self-signed entity statement that is defined in OpenID Connect
  *   Federation 1.0.
  * @param federationEnabled
  *   flag indicating whether this service supports OpenID Connect Federation 1
  * @param federationJwks
  *   JWK Set document containing keys that are used to sign (1) self-signed
  *   entity statement of this service and (2) the response from
  *   `signed_jwks_uri`.
  * @param federationSignatureKeyId
  *   A key ID to identify a JWK used to sign the entity configuration and the
  *   signed JWK Set.
  * @param federationConfigurationDuration
  *   The duration of the entity configuration in seconds.
  * @param federationRegistrationEndpoint
  *   The URI of the federation registration endpoint. This property corresponds
  *   to the `federation_registration_endpoint` server metadata that is defined
  *   in OpenID Connect Federation 1.0.
  * @param organizationName
  *   The human-readable name representing the organization that operates this
  *   service. This property corresponds to the `organization_name` server
  *   metadata that is defined in OpenID Connect Federation 1.0.
  * @param predefinedTransformedClaims
  *   The transformed claims predefined by this service in JSON format. This
  *   property corresponds to the `transformed_claims_predefined` server
  *   metadata.
  * @param refreshTokenIdempotent
  *   flag indicating whether refresh token requests with the same refresh token
  *   can be made multiple times in quick succession and they can obtain the
  *   same renewed refresh token within the short period.
  * @param signedJwksUri
  *   The URI of the endpoint that returns this service\'s JWK Set document in
  *   the JWT format. This property corresponds to the `signed_jwks_uri` server
  *   metadata defined in OpenID Connect Federation 1.0.
  * @param supportedAttachments
  *   Supported attachment types. This property corresponds to the
  *   {@@@@@@@codeattachments_supported} server metadata which was added by the
  *   third implementer\'s draft of OpenID Connect for Identity Assurance 1.0.
  * @param supportedDigestAlgorithms
  *   Supported algorithms used to compute digest values of external
  *   attachments. This property corresponds to the
  *   `digest_algorithms_supported` server metadata which was added by the third
  *   implementer\'s draft of OpenID Connect for Identity Assurance 1.0.
  * @param supportedDocuments
  *   Document types supported by this service. This property corresponds to the
  *   `documents_supported` server metadata.
  * @param supportedDocumentsMethods
  *   validation and verification processes supported by this service. This
  *   property corresponds to the `documents_methods_supported` server metadata.
  *   The third implementer\'s draft of [OpenID Connect for Identity Assurance
  *   1.0](https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html)
  *   renamed the `id_documents_verification_methods_supported` server metadata
  *   to `documents_methods_supported`.
  * @param supportedDocumentsValidationMethods
  *   Document validation methods supported by this service. This property
  *   corresponds to the `documents_validation_methods_supported` server
  *   metadata which was added by the third implementer\'s draft of <a href=
  *   [OpenID Connect for Identity Assurance
  *   1.0](https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html)
  * @param supportedDocumentsVerificationMethods
  *   Document verification methods supported by this service. This property
  *   corresponds to the `documents_verification_methods_supported` server
  *   metadata which was added by the third implementer\'s draft of [OpenID
  *   Connect for Identity Assurance
  *   1.0](https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html)
  * @param supportedElectronicRecords
  *   Electronic record types supported by this service. This property
  *   corresponds to the `electronic_records_supported` server metadata which
  *   was added by the third implementer\'s draft of [OpenID Connect for
  *   Identity Assurance
  *   1.0](https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html)
  * @param supportedClientRegistrationTypes
  *
  * @param tokenExchangeByIdentifiableClientsOnly
  *   The flag indicating whether to prohibit unidentifiable clients from making
  *   token exchange requests.
  * @param tokenExchangeByConfidentialClientsOnly
  *   The flag indicating whether to prohibit public clients from making token
  *   exchange requests.
  * @param tokenExchangeByPermittedClientsOnly
  *   The flag indicating whether to prohibit clients that have no explicit
  *   permission from making token exchange requests.
  * @param tokenExchangeEncryptedJwtRejected
  *   The flag indicating whether to reject token exchange requests which use
  *   encrypted JWTs as input tokens.
  * @param tokenExchangeUnsignedJwtRejected
  *   The flag indicating whether to reject token exchange requests which use
  *   unsigned JWTs as input tokens.
  * @param jwtGrantByIdentifiableClientsOnly
  *   The flag indicating whether to prohibit unidentifiable clients from using
  *   the grant type \"urn:ietf:params:oauth:grant-type:jwt-bearer\".
  * @param jwtGrantEncryptedJwtRejected
  *   The flag indicating whether to reject token requests that use an encrypted
  *   JWT as an authorization grant with the grant type
  *   \"urn:ietf:params:oauth:grant-type:jwt-bearer\".
  * @param jwtGrantUnsignedJwtRejected
  *   The flag indicating whether to reject token requests that use an unsigned
  *   JWT as an authorization grant with the grant type
  *   \"urn:ietf:params:oauth:grant-type:jwt-bearer\".
  * @param dcrDuplicateSoftwareIdBlocked
  *   The flag indicating whether to block DCR (Dynamic Client Registration)
  *   requests whose \"software_id\" has already been used previously.
  * @param trustAnchors
  *   The trust anchors that are referenced when this service resolves trust
  *   chains of relying parties. If this property is empty, client registration
  *   fails regardless of whether its type is `automatic` or `explicit`. It
  *   means that OpenID Connect Federation 1.0 does not work.
  * @param openidDroppedOnRefreshWithoutOfflineAccess
  *   The flag indicating whether the openid scope should be dropped from scopes
  *   list assigned to access token issued when a refresh token grant is used.
  * @param supportedDocumentsCheckMethods
  *   Supported document check methods. This property corresponds to the
  *   `documents_check_methods_supported` server metadata which was added by the
  *   fourth implementer\'s draft of OpenID Connect for Identity Assurance 1.0.
  * @param resourceSignatureKeyId
  *   The key ID of a JWK containing the private key used by this service to
  *   sign responses from the resource server.
  * @param rsResponseSigned
  *   The flag indicating whether this service signs responses from the resource
  *   server.
  */
final case class Service(
    number: Option[Long] = None,
    serviceOwnerNumber: Option[Long] = None,
    serviceName: Option[String] = None,
    issuer: Option[String] = None,
    description: Option[String] = None,
    apiKey: Option[String] = None,
    apiSecret: Option[String] = None,
    clientsPerDeveloper: Option[Long] = None,
    clientIdAliasEnabled: Option[Boolean] = None,
    metadata: Option[String] = None,
    createdAt: Option[Long] = None,
    modifiedAt: Option[Long] = None,
    authenticationCallbackEndpoint: Option[String] = None,
    authenticationCallbackApiKey: Option[String] = None,
    authenticationCallbackApiSecret: Option[String] = None,
    supportedSnses: Option[List[Sns]] = None,
    snsCredentials: Option[List[SnsCredentials]] = None,
    supportedAcrs: Option[List[String]] = None,
    developerAuthenticationCallbackEndpoint: Option[String] = None,
    developerAuthenticationCallbackApiKey: Option[String] = None,
    developerAuthenticationCallbackApiSecret: Option[String] = None,
    supportedDeveloperSnses: Option[List[Sns]] = None,
    developerSnsCredentials: Option[List[SnsCredentials]] = None,
    supportedGrantTypes: Option[List[GrantType]] = None,
    supportedResponseTypes: Option[List[ResponseType]] = None,
    supportedAuthorizationDetailsTypes: Option[List[String]] = None,
    supportedServiceProfiles: Option[List[ServiceProfile]] = None,
    errorDescriptionOmitted: Option[Boolean] = None,
    errorUriOmitted: Option[Boolean] = None,
    authorizationEndpoint: Option[String] = None,
    directAuthorizationEndpointEnabled: Option[Boolean] = None,
    supportedUiLocales: Option[List[String]] = None,
    supportedDisplays: Option[List[Display]] = None,
    pkceRequired: Option[Boolean] = None,
    pkceS256Required: Option[Boolean] = None,
    authorizationResponseDuration: Option[Long] = None,
    tokenEndpoint: Option[String] = None,
    directTokenEndpointEnabled: Option[Boolean] = None,
    supportedTokenAuthMethods: Option[List[ClientAuthMethod]] = None,
    missingClientIdAllowed: Option[Boolean] = None,
    revocationEndpoint: Option[String] = None,
    directRevocationEndpointEnabled: Option[Boolean] = None,
    supportedRevocationAuthMethods: Option[List[ClientAuthMethod]] = None,
    introspectionEndpoint: Option[String] = None,
    directIntrospectionEndpointEnabled: Option[Boolean] = None,
    supportedIntrospectionAuthMethods: Option[List[ClientAuthMethod]] = None,
    pushedAuthReqEndpoint: Option[String] = None,
    pushedAuthReqDuration: Option[Long] = None,
    parRequired: Option[Boolean] = None,
    requestObjectRequired: Option[Boolean] = None,
    traditionalRequestObjectProcessingApplied: Option[Boolean] = None,
    mutualTlsValidatePkiCertChain: Option[Boolean] = None,
    trustedRootCertificates: Option[List[String]] = None,
    mtlsEndpointAliases: Option[List[NamedUri]] = None,
    accessTokenType: Option[String] = None,
    tlsClientCertificateBoundAccessTokens: Option[Boolean] = None,
    accessTokenDuration: Option[Long] = None,
    singleAccessTokenPerSubject: Option[Boolean] = None,
    accessTokenSignAlg: Option[JwsAlg] = None,
    accessTokenSignatureKeyId: Option[String] = None,
    refreshTokenDuration: Option[Long] = None,
    refreshTokenDurationKept: Option[Boolean] = None,
    refreshTokenDurationReset: Option[Boolean] = None,
    refreshTokenKept: Option[Boolean] = None,
    supportedScopes: Option[List[Scope]] = None,
    scopeRequired: Option[Boolean] = None,
    idTokenDuration: Option[Long] = None,
    allowableClockSkew: Option[Long] = None,
    supportedClaimTypes: Option[List[ClaimType]] = None,
    supportedClaimLocales: Option[List[String]] = None,
    supportedClaims: Option[List[String]] = None,
    claimShortcutRestrictive: Option[Boolean] = None,
    jwksUri: Option[String] = None,
    directJwksEndpointEnabled: Option[Boolean] = None,
    jwks: Option[String] = None,
    idTokenSignatureKeyId: Option[String] = None,
    userInfoSignatureKeyId: Option[String] = None,
    authorizationSignatureKeyId: Option[String] = None,
    userInfoEndpoint: Option[String] = None,
    directUserInfoEndpointEnabled: Option[Boolean] = None,
    dynamicRegistrationSupported: Option[Boolean] = None,
    registrationEndpoint: Option[String] = None,
    registrationManagementEndpoint: Option[String] = None,
    policyUri: Option[String] = None,
    tosUri: Option[String] = None,
    serviceDocumentation: Option[String] = None,
    backchannelAuthenticationEndpoint: Option[String] = None,
    supportedBackchannelTokenDeliveryModes: Option[List[DeliveryMode]] = None,
    backchannelAuthReqIdDuration: Option[Long] = None,
    backchannelPollingInterval: Option[Long] = None,
    backchannelUserCodeParameterSupported: Option[Boolean] = None,
    backchannelBindingMessageRequiredInFapi: Option[Boolean] = None,
    deviceAuthorizationEndpoint: Option[String] = None,
    deviceVerificationUri: Option[String] = None,
    deviceVerificationUriComplete: Option[String] = None,
    deviceFlowCodeDuration: Option[Long] = None,
    deviceFlowPollingInterval: Option[Long] = None,
    userCodeCharset: Option[UserCodeCharset] = None,
    userCodeLength: Option[Long] = None,
    supportedTrustFrameworks: Option[List[String]] = None,
    supportedEvidence: Option[List[String]] = None,
    supportedIdentityDocuments: Option[List[String]] = None,
    supportedVerificationMethods: Option[List[String]] = None,
    supportedVerifiedClaims: Option[List[String]] = None,
    verifiedClaimsValidationSchemaSet: Option[VerifiedClaimsValidationSchema] =
      None,
    attributes: Option[List[Pair]] = None,
    nbfOptional: Option[Boolean] = None,
    issSuppressed: Option[Boolean] = None,
    supportedCustomClientMetadata: Option[List[String]] = None,
    tokenExpirationLinked: Option[Boolean] = None,
    frontChannelRequestObjectEncryptionRequired: Option[Boolean] = None,
    requestObjectEncryptionAlgMatchRequired: Option[Boolean] = None,
    requestObjectEncryptionEncMatchRequired: Option[Boolean] = None,
    hsmEnabled: Option[Boolean] = None,
    hsks: Option[List[Hsk]] = None,
    grantManagementEndpoint: Option[String] = None,
    grantManagementActionRequired: Option[Boolean] = None,
    unauthorizedOnClientConfigSupported: Option[Boolean] = None,
    dcrScopeUsedAsRequestable: Option[Boolean] = None,
    endSessionEndpoint: Option[String] = None,
    loopbackRedirectionUriVariable: Option[Boolean] = None,
    requestObjectAudienceChecked: Option[Boolean] = None,
    accessTokenForExternalAttachmentEmbedded: Option[Boolean] = None,
    authorityHints: Option[List[String]] = None,
    federationEnabled: Option[Boolean] = None,
    federationJwks: Option[String] = None,
    federationSignatureKeyId: Option[String] = None,
    federationConfigurationDuration: Option[Long] = None,
    federationRegistrationEndpoint: Option[String] = None,
    organizationName: Option[String] = None,
    predefinedTransformedClaims: Option[String] = None,
    refreshTokenIdempotent: Option[Boolean] = None,
    signedJwksUri: Option[String] = None,
    supportedAttachments: Option[List[AttachmentType]] = None,
    supportedDigestAlgorithms: Option[List[String]] = None,
    supportedDocuments: Option[List[String]] = None,
    supportedDocumentsMethods: Option[List[String]] = None,
    supportedDocumentsValidationMethods: Option[List[String]] = None,
    supportedDocumentsVerificationMethods: Option[List[String]] = None,
    supportedElectronicRecords: Option[List[String]] = None,
    supportedClientRegistrationTypes: Option[List[ClientRegistrationType]] =
      None,
    tokenExchangeByIdentifiableClientsOnly: Option[Boolean] = None,
    tokenExchangeByConfidentialClientsOnly: Option[Boolean] = None,
    tokenExchangeByPermittedClientsOnly: Option[Boolean] = None,
    tokenExchangeEncryptedJwtRejected: Option[Boolean] = None,
    tokenExchangeUnsignedJwtRejected: Option[Boolean] = None,
    jwtGrantByIdentifiableClientsOnly: Option[Boolean] = None,
    jwtGrantEncryptedJwtRejected: Option[Boolean] = None,
    jwtGrantUnsignedJwtRejected: Option[Boolean] = None,
    dcrDuplicateSoftwareIdBlocked: Option[Boolean] = None,
    trustAnchors: Option[List[TrustAnchor]] = None,
    openidDroppedOnRefreshWithoutOfflineAccess: Option[Boolean] = None,
    supportedDocumentsCheckMethods: Option[List[String]] = None,
    resourceSignatureKeyId: Option[String] = None,
    rsResponseSigned: Option[Boolean] = None
) derives Codec.AsObject
