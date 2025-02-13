package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class JoseObjectEndpoints() extends BaseEndpoint {

  /** This API verifies a JOSE object. /api/jose/verify API
    */
  val joseVerify =
    endpoint.post
      .in("api" / "jose" / "verify")
      // .in(jsonBody[JoseVerifyApiRequest])
      .out(jsonBody[JoseVerifyResponse])
      .errorOut(emptyOutput)

  case class JoseVerifyApiRequest(joseVerifyRequest: Option[JoseVerifyRequest])
}
