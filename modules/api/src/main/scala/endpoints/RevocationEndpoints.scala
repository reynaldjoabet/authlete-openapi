package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class RevocationEndpoints() extends BaseEndpoint {

  /**
    * This API revokes access tokens and refresh tokens. /api/auth/revocation API
    */
  val authRevocation =
    endpoint
      .post
      .in("api" / "auth" / "revocation")
      // .in(jsonBody[RevocationRequest])
      .out(jsonBody[RevocationResponse])
      .errorOut(emptyOutput)

}
