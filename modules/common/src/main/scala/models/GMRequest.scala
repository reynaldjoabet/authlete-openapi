package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param accessToken
  *   An access token to introspect. Determines if the provided token is valid and has the necessary
  *   permissions.
  * @param scopes
  *   A list of scope names required by the protected resource. If any required scope is missing in
  *   the token, an `insufficient_scope` error is returned.
  * @param subject
  *   A subject (user account) that the protected resource requires. If specified and does not match
  *   the subject associated with the token, an `invalid_request` error occurs.
  * @param clientCertificate
  *   A client certificate in PEM format. Used for validating TLS client certificate-bound access
  *   tokens.
  * @param dpop
  *   The `DPoP` header presented by the client. Contains a signed JWT with the public key used for
  *   Proof-of-Possession. See [OAuth 2.0
  *   DPoP](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-dpop).
  * @param htm
  *   HTTP method of the request to the protected resource. Used for validating the `DPoP` header.
  * @param htu
  *   URL of the protected resource endpoint. Used for validating the `DPoP` header.
  * @param resources
  *   The resources specified by the `resource` parameter in the token request. See [Resource
  *   Indicators for OAuth
  *   2.0](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-resource-indicators).
  * @param gmAction
  *   The action related to Grant Management. Defines fine-grained access control behavior.
  * @param grantId
  *   The `grant_id` from a device authorization request. Defined in [Grant Management for OAuth
  *   2.0](https://openid.net/specs/fapi-grant-management.html). Supported in Authlete 2.3+.
  */
final case class GMRequest(
    accessToken: Option[String] = None,
    scopes: Option[List[String]] = None,
    subject: Option[String] = None,
    clientCertificate: Option[String] = None,
    dpop: Option[String] = None,
    htm: Option[String] = None,
    htu: Option[String] = None,
    resources: Option[List[String]] = None,
    gmAction: Option[GrantManagementAction] = None,
    grantId: Option[String] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object GMRequest {
  // implicit val codec: JsonValueCodec[GMRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
