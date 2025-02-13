package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*
final case class RevocationEndpoints() extends BaseEndpoint {

  /** This API revokes access tokens and refresh tokens. /api/auth/revocation
    * API
    */
  val authRevocation =
    endpoint.post
      .in("api" / "auth" / "revocation")
      // .in(jsonBody[RevocationRequest])
      .out(jsonBody[RevocationResponse])
      .errorOut(emptyOutput)

case class AuthRevocationApiRequest(revocationRequest: RevocationRequest)
}
