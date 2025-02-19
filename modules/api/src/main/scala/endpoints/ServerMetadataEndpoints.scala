package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class ServerMetadataEndpoints() extends BaseEndpoint {

  /**
    * This API gets the server version and enabled features. /api/info API
    */
  val info =
    endpoint.get.in("api" / "info").out(jsonBody[InfoResponse]).errorOut(emptyOutput)

}
