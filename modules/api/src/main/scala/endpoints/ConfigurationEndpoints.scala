package endpoints

import scala.concurrent.ExecutionContext.Implicits.global

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*
import sttp.tapir.Schema

final case class ConfigurationEndpoints() extends BaseEndpoint {

  /**
    * This API gathers configuration information about a service. /api/service/configuration API
    */
  val serviceConfiguration =
    endpoint
      .get
      .in("api" / "service" / "configuration")
      .in(query[Option[Boolean]]("pretty"))
      .in(header[String]("Content-Type"))
      .in(header[String]("Authorization"))
      .out(jsonBody[String])
      .errorOut(emptyOutput)

}
