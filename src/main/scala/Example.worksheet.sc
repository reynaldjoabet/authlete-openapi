import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import common.models.*
import common.models.HskGetListResponse.HskGetListResponseAction
import common.models.JsoniterSyntaticSugar.*
import sttp.tapir.Schema

val service = """{
  "accessTokenDuration": 3600,
  "accessTokenType": "Bearer",
  "allowableClockSkew": 0,
  "apiKey": 21653835348762,
  "attributes": [
    {
      "key": "attribute1-key",
      "value": "attribute1-value"
    },
    {
      "key": "attribute2-key",
      "value": "attribute2-value"
    }
  ],
  "authorizationEndpoint": "https://my-service.example.com/authz",
  "authorizationResponseDuration": 0,
  "backchannelAuthReqIdDuration": 0,
  "backchannelBindingMessageRequiredInFapi": false,
  "backchannelPollingInterval": 0,
  "backchannelUserCodeParameterSupported": false,
  "claimShortcutRestrictive": false,
  "clientIdAliasEnabled": true,
  "clientsPerDeveloper": 0,
  "createdAt": 1639373421000,
  "dcrScopeUsedAsRequestable": false,
  "deviceFlowCodeDuration": 0,
  "deviceFlowPollingInterval": 0,
  "directAuthorizationEndpointEnabled": false,
  "directIntrospectionEndpointEnabled": false,
  "directJwksEndpointEnabled": false,
  "directRevocationEndpointEnabled": false,
  "directTokenEndpointEnabled": false,
  "directUserInfoEndpointEnabled": false,
  "dynamicRegistrationSupported": false,
  "errorDescriptionOmitted": false,
  "errorUriOmitted": false,
  "frontChannelRequestObjectEncryptionRequired": false,
  "grantManagementActionRequired": false,
  "hsmEnabled": false,
  "idTokenDuration": 0,
  "introspectionEndpoint": "https://my-service.example.com/introspection",
  "issSuppressed": false,
  "issuer": "https://my-service.example.com",
  "metadata": [
    {
      "key": "clientCount",
      "value": "1"
    }
  ],
  "missingClientIdAllowed": false,
  "modifiedAt": 1639373421000,
  "mutualTlsValidatePkiCertChain": false,
  "nbfOptional": false,
  "number": 5041,
  "parRequired": false,
  "pkceRequired": true,
  "pkceS256Required": false,
  "pushedAuthReqDuration": 0,
  "refreshTokenDuration": 3600,
  "refreshTokenDurationKept": false,
  "refreshTokenDurationReset": false,
  "refreshTokenKept": false,
  "requestObjectEncryptionAlgMatchRequired": false,
  "requestObjectEncryptionEncMatchRequired": false,
  "requestObjectRequired": false,
  "revocationEndpoint": "https://my-service.example.com/revocation",
  "scopeRequired": false,
  "serviceName": "My service",
  "serviceOwnerNumber": 2,
  "singleAccessTokenPerSubject": false,
  "supportedClaimTypes": [
    "NORMAL"
  ],
  "supportedDisplays": [
    "PAGE"
  ],
  "supportedGrantTypes": [
    "AUTHORIZATION_CODE",
    "REFRESH_TOKEN"
  ],
  "supportedIntrospectionAuthMethods": [
    "CLIENT_SECRET_BASIC"
  ],
  "supportedResponseTypes": [
    "CODE"
  ],
  "supportedRevocationAuthMethods": [
    "CLIENT_SECRET_BASIC"
  ],
  "supportedScopes": [
    {
      "defaultEntry": false,
      "description": "A permission to read your history.",
      "name": "history.read"
    },
    {
      "defaultEntry": false,
      "description": "A permission to read your timeline.",
      "name": "timeline.read"
    }
  ],
  "supportedTokenAuthMethods": [
    "CLIENT_SECRET_BASIC"
  ],
  "tlsClientCertificateBoundAccessTokens": false,
  "tokenEndpoint": "https://my-service.example.com/token",
  "tokenExpirationLinked": false,
  "traditionalRequestObjectProcessingApplied": false,
  "unauthorizedOnClientConfigSupported": false,
  "userCodeLength": 0
}""".stripMargin

val ser = readFromString[Service](service)

ser.supportedGrantTypes

val authAuthorizationRequest = """{
  "parameters": "response_type=code&client_id=26478243745571&redirect_uri=https%3A%2F%2Fmy-client.example.com%2Fcb1&scope=timeline.read+history.read&code_challenge=E9Melhoa2OwvFrEMTJguCHaoeK1t8URWbuGJSstw-cM&code_challenge_method=S256"
}""".stripMargin

readFromString[AuthorizationRequest](authAuthorizationRequest)

val authAuthorizationResponse = """{
  "resultCode": "A004001",
  "resultMessage": "[A004001] Authlete has successfully issued a ticket to the service (API Key = 21653835348762) for the authorization request from the client (ID = 26478243745571). [response_type=code, openid=false]",
  "acrEssential": false,
  "action": "INTERACTION",
  "client": {
    "clientId": 26478243745571,
    "clientIdAlias": "my-client",
    "clientIdAliasEnabled": true,
    "clientName": "My updated client",
    "logo_uri": "https://my-client.example.com/logo.png",
    "number": 6164
  },
  "clientIdAliasUsed": false,
  "display": "PAGE",
  "maxAge": 0,
  "scopes": [
    {
      "defaultEntry": false,
      "description": "A permission to read your history.",
      "name": "history.read"
    },
    {
      "defaultEntry": false,
      "description": "A permission to read your timeline.",
      "name": "timeline.read"
    }
  ],
  "service": {
    "apiKey": 21653835348762,
    "clientIdAliasEnabled": true,
    "number": 5041,
    "serviceName": "My updated service"
  },
  "ticket": "hXoY87t_t23enrVHWxpXNP5FfVDhDypD3T6H6lt4IPA"
}""".stripMargin

//readFromArray(authAuthorizationResponse)

val data = readFromString[AuthorizationResponse](authAuthorizationResponse)

data.gmAction

data.grant

summon[Schema[Service]]

summon[Schema[AccessToken]]

summon[Schema[Grant]]

summon[Schema[GrantType]]

summon[Schema[Client]]

//Int.MaxValue - 26478243745571

Long.MaxValue

Long.MaxValue - 26478243745571L

26478243745571L

val auth = AuthorizationResponse(
  "A004001",
  "[A004001] Authlete has successfully issued a ticket to the service (API Key = 21653835348762) for the authorization request from the client (ID = 26478243745571). [response_type=code, openid=false]",
  Some(AuthorizationResponse.AuthorizationResponseAction.Interaction),
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None,
  None
)

auth

writeToString(auth)

auth.toJson

val authorizationFailRequest = """{
  "ticket": "qA7wGybwArICpbUSutrf5Xc9-i1fHE0ySOHxR1eBoBQ",
  "reason": "NOT_AUTHENTICATED"
}"""

val fail = readFromString[AuthorizationFailRequest](authorizationFailRequest)

fail.reason

val authorizationFailResponse = """{
  "resultCode": "A004201",
  "resultMessage": "[A004201] The authorization request from the service does not contain 'parameters' parameter.",
  "action": "BAD_REQUEST",
  "responseContent": "{\\\"error_description\\\":\\\"[A004201] The authorization request from the service does not contain 'parameters' parameter.\\\",\\\"error\\\":\\\"invalid_request\\\",\\\"error_uri\\\":\\\"https://docs.authlete.com/#A004201\\\"}"
}""".stripMargin

val authorizationFailResponse2 =
  readFromString[AuthorizationFailResponse](authorizationFailResponse)

authorizationFailResponse2.action

authorizationFailResponse2.resultMessage

val issue = """{
  "ticket": "FFgB9gwb_WXh6g1u-UQ8ZI-d_k4B-o-cm7RkVzI8Vnc",
  "subject": "john"
}""".stripMargin

readFromString[AuthorizationIssueRequest](issue)

val issueResponse = """{
  "resultCode": "A040001",
  "resultMessage": "[A040001] The authorization request was processed successfully.",
  "accessTokenDuration": 0,
  "accessTokenExpiresAt": 0,
  "action": "LOCATION",
  "authorizationCode": "Xv_su944auuBgc5mfUnxXayiiQU9Z4-T_Yae_UfExmo",
  "responseContent": "https://my-client.example.com/cb1?code=Xv_su944auuBgc5mfUnxXayiiQU9Z4-T_Yae_UfExmo&iss=https%3A%2F%2Fmy-service.example.com"
}""".stripMargin

readFromString[AuthorizationIssueResponse](issueResponse)

val pushed = """{
  "parameters": "response_type=code%20id_token&client_id=5921531358155430&redirect_uri=https%3A%2F%2Fserver.example.com%2Fcb&state=SOME_VALUE_ABLE_TO_PREVENT_CSRF&scope=openid&nonce=SOME_VALUE_ABLE_TO_PREVENT_REPLAY_ATTACK&code_challenge=5ZWDQJiryK3eaLtSeFV8y1XySMCWtyITxICLaTwvK8g&code_challenge_method=S256",
  "clientId": "5921531358155430",
  "clientSecret": "P_FouxWlI7zcOep_9vBwR9qMAVJQiCiUiK1HrAP4GziOyezHQpqY0f5dHXK4JT4tnvI51OkbWVoEM9GnOyJViA"
}""".stripMargin

readFromString[PushedAuthReqRequest](pushed)

val pushedResponse = """{
  "resultCode": "A245001",
  "resultMessage": "[A245001] Successfully registered a request object for client (5921531358155430), URI is urn:ietf:params:oauth:request_uri:CAK9YEtNorwXE3UwSyihsBOL0jFrqUup7yAACw5y5Zg.",
  "action": "CREATED",
  "requestUri": "urn:ietf:params:oauth:request_uri:CAK9YEtNorwXE3UwSyihsBOL0jFrqUup7yAACw5y5Zg",
  "responseContent": "{\"expires_in\":600,\"request_uri\":\"urn:ietf:params:oauth:request_uri:CAK9YEtNorwXE3UwSyihsBOL0jFrqUup7yAACw5y5Zg\"}"
}""".stripMargin

readFromString[PushedAuthReqResponse](pushedResponse)

val token = """{
  "parameters": "grant_type=authorization_code&code=Xv_su944auuBgc5mfUnxXayiiQU9Z4-T_Yae_UfExmo&redirect_uri=https%3A%2F%2Fmy-client.example.com%2Fcb1&code_verifier=dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk",
  "clientId": "26478243745571",
  "clientSecret": "gXz97ISgLs4HuXwOZWch8GEmgL4YMvUJwu3er_kDVVGcA0UOhA9avLPbEmoeZdagi9yC_-tEiT2BdRyH9dbrQQ"
}""".stripMargin

readFromString[TokenRequest](token)

val tokenResponse = """{
  "resultCode": "A050001",
  "resultMessage": "[A050001] The token request (grant_type=authorization_code) was processed successfully.",
  "accessToken": "C4SrUTijIj2IxqE1xBASr3dxQWgso3BpY49g8CyjGjQ",
  "accessTokenDuration": 3600,
  "accessTokenExpiresAt": 1640252942736,
  "action": "OK",
  "clientAttributes": [
    {
      "key": "attribute1-key",
      "value": "attribute1-value"
    },
    {
      "key": "attribute2-key",
      "value": "attribute2-value"
    }
  ],
  "clientId": 26478243745571,
  "clientIdAlias": "my-client",
  "clientIdAliasUsed": false,
  "grantType": "AUTHORIZATION_CODE",
  "refreshToken": "60k0cZ38sJcpTgdxvG9Sqa-3RG5AmGExGpFB-1imSxo",
  "refreshTokenDuration": 3600,
  "refreshTokenExpiresAt": 1640252942736,
  "responseContent": "{\\\"access_token\\\":\\\"C4SrUTijIj2IxqE1xBASr3dxQWgso3BpY49g8CyjGjQ\\\",\\\"refresh_token\\\":\\\"60k0cZ38sJcpTgdxvG9Sqa-3RG5AmGExGpFB-1imSxo\\\",\\\"scope\\\":\\\"history.read timeline.read\\\",\\\"token_type\\\":\\\"Bearer\\\",\\\"expires_in\\\":3600}",
  "scopes": [
    "history.read",
    "timeline.read"
  ],
  "serviceAttributes": [
    {
      "key": "attribute1-key",
      "value": "attribute1-value"
    },
    {
      "key": "attribute2-key",
      "value": "attribute2-value"
    }
  ],
  "subject": "john"
}""".stripMargin

readFromString[TokenResponse](tokenResponse).action

val createHsk = """{
  "kty": "string",
  "use": "string",
  "kid": "string",
  "hsmName": "string",
  "handle": "string",
  "publicKey": "string"
}""".stripMargin

readFromString[HskCreateRequest](createHsk)

val createHskRespones = """{
  "resultCode": "string",
  "resultMessage": "string",
  "action": "SUCCESS",
  "hsk": {
    "kty": "string",
    "use": "string",
    "kid": "string",
    "hsmName": "string",
    "handle": "string",
    "publicKey": "string"
  }
}""".stripMargin

readFromString[HskCreateResponse](createHskRespones)

val gethsk = """{
  "resultCode": "string",
  "resultMessage": "string",
  "action": "SUCCESS",
  "hsk": {
    "kty": "string",
    "use": "string",
    "kid": "string",
    "hsmName": "string",
    "handle": "string",
    "publicKey": "string"
  }
}""".stripMargin

readFromString[HskGetResponse](gethsk)

val hsks = """{
  "resultCode": "string",
  "resultMessage": "string",
  "action": "SUCCESS",
  "hsks": [
    {
      "kty": "string",
      "use": "string",
      "kid": "string",
      "hsmName": "string",
      "handle": "string",
      "publicKey": "string"
    }
  ]
}""".stripMargin

readFromString[HskGetListResponse](hsks).action.get match {
  case HskGetListResponseAction.Success        => 2
  case HskGetListResponseAction.InvalidRequest => 3
  case HskGetListResponseAction.ServerError    => 4
}

val backchannelAuthenticationResponse = """{
  "resultCode": "A179001",
  "resultMessage": "[A179001] The backchannel authentication request was processed successfully.",
  "action": "USER_IDENTIFICATION",
  "clientId": 26862190133482,
  "clientIdAliasUsed": false,
  "clientName": "My CIBA Client",
  "clientNotificationToken": "my-client-notification-token",
  "deliveryMode": "POLL",
  "hint": "john",
  "hintType": "LOGIN_HINT",
  "requestedExpiry": 0,
  "scopes": [
    {
      "defaultEntry": false,
      "name": "openid"
    }
  ],
  "serviceAttributes": [
    {
      "key": "attribute1-key",
      "value": "attribute1-value"
    },
    {
      "key": "attribute2-key",
      "value": "attribute2-value"
    }
  ],
  "ticket": "Y1qeCf0A-JUz6caceaBfd2AaBYNZ-X-WGTP5Qv47cQI",
  "userCode": "my-user-code",
  "userCodeRequired": false
}""".stripMargin

readFromString[BackchannelAuthenticationResponse](backchannelAuthenticationResponse)

import sttp.model.Uri

private val baseUri = Uri("https", "config.host", 8090)

"[A001201] /auth/authorization, TLS must be used.".split(",")(1)