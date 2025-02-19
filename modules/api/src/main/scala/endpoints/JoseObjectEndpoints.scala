package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class JoseObjectEndpoints() extends BaseEndpoint {

  /**
    * This API verifies a JOSE object. /api/jose/verify API
    */
  val joseVerify =
    endpoint
      .post
      .in("api" / "jose" / "verify")
      .in(jsonBody[JoseVerifyRequest])
      .out(jsonBody[JoseVerifyResponse])
      .errorOut(emptyOutput)

}
