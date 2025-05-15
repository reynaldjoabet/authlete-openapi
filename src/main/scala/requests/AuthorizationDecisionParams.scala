package requests

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import common.models.*
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Handler for end-user's decision on the authorization request.
  *
  * <p> An authorization endpoint returns an authorization page (HTML) to an end-user, and the
  * end-user will select either "authorize" or "deny" the authorization request. This class handles
  * the decision and calls Authlete's {@code /api/auth/authorization/issue} API or
  * {@code /api/auth/authorization/fail} API accordingly. </p>
  * @param ticket
  *   A ticket that was issued by Authlete's {@code /api/auth/authorization} API.
  * @param claimNames
  *   Names of requested claims. Use the value of the {@code claims} parameter in a response from
  *   Authlete's {@code /api/auth/authorization} API.
  * @param claimLocales
  *   Requested claim locales. Use the value of the {@code claimsLocales} parameter in a response
  *   from Authlete's {@code /api/auth/authorization} API.
  * @param idTokenClaims
  *   The value of the {@code id_token} property in the {@code claims} request parameter.
  * @param requestedClaimsForTx
  *   The claims that are indirectly requested by transformed claims.
  * @param requestedVerifiedClaimsForTx
  *   The verified claims that are indirectly requested by transformed claims.
  * @param oldIdaFormatUsed
  *   The flag indicating whether {@link AuthorizationDecisionHandler} uses the old format of
  *   {@code "verified_claims"} defined in the Implementer's Draft 2 of OpenID Connect for Identity
  *   Assurance 1&#x002E;0 which was published on May 19, 2020.
  */
final case class AuthorizationDecisionParams(
    ticket: String,
    claimNames: List[String],
    claimLocales: List[String],
    idTokenClaims: Option[String],
    requestedClaimsForTx: List[String],
    requestedVerifiedClaimsForTx: List[List[String]],
    oldIdaFormatUsed: Boolean
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
