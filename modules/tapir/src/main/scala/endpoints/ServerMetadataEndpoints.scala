package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*

final case class ServerMetadataEndpoints() extends BaseEndpoint {

  /** This API gets the server version and enabled features. /api/info API
    */
  val info =
    endpoint.get
      .in("api" / "info")
      .out(jsonBody[InfoResponse])
      .errorOut(emptyOutput)

    // case class InfoResponse(/* Define the fields based on your model */)
}
