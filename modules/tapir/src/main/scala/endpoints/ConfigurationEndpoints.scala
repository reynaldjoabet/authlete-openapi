package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*
final case class ConfigurationEndpoints() extends BaseEndpoint {

  /** This API gathers configuration information about a service.
    * /api/service/configuration API
    */
  val serviceConfiguration =
    endpoint.get
      .in("api" / "service" / "configuration")
      .in(query[Option[Boolean]]("pretty"))
      //.in(header[String]("Content-Type"))
      //.in(header[String]("Authorization"))
      .out(jsonBody[String])
      .errorOut(emptyOutput)

  case class ServiceConfigurationApiRequest(pretty: Option[Boolean])
}
