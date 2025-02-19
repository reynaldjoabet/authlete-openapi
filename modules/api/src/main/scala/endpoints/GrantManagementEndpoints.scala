package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class GrantManagementEndpoints() extends BaseEndpoint {

  case class GrantMApiRequest(gMRequest: GMRequest)

  /**
    * The API is for the implementation of the grant management endpoint which is defined in "Grant
    * Management for OAuth 2.0". /api/gm API
    */
  val grantM =
    endpoint
      .post
      .in("api" / "gm")
      .in(jsonBody[GMRequest])
      .out(jsonBody[GMResponse])
      .errorOut(emptyOutput)

}
