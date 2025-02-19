package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*
import sttp.tapir.json.jsoniter.jsonBody

final case class ServiceManagementEndpoints() extends BaseEndpoint {

  /**
    * Create a new service. /api/service/create API
    */
  val serviceCreate =
    endpoint
      .post
      .in("api" / "service" / "create")
      // .in(jsonBody[ServiceCreateRequest])
      .out(jsonBody[Service])
      .errorOut(emptyOutput)

  /**
    * Delete a service. /api/service/delete/{serviceApiKey} API
    */
  val serviceDelete =
    endpoint
      .delete
      .in("api" / "service" / "delete" / path[String]("serviceApiKey"))
      // .in(jsonBody[ServiceDeleteRequest])
      .out(emptyOutput)
      .errorOut(emptyOutput)

  /**
    * Get a service. /api/service/get/{serviceApiKey} API
    */
  val serviceGet =
    endpoint
      .get
      .in("api" / "service" / "get" / path[String]("serviceApiKey"))
      // .in(jsonBody[ServiceGetRequest])
      .out(jsonBody[Service])
      .errorOut(emptyOutput)

  /**
    * Get a list of services. /api/service/get/list API
    */
  val serviceGetList =
    endpoint
      .get
      .in("api" / "service" / "get" / "list")
      .in(query[Option[Int]]("start"))
      .in(query[Option[Int]]("end"))
      // .in(jsonBody[ServiceGetListRequest])
      .out(jsonBody[ServiceGetListResponse])
      .errorOut(emptyOutput)

  /**
    * Update a service. /api/service/update/{serviceApiKey} API
    */
  val serviceUpdate =
    endpoint
      .post
      .in("api" / "service" / "update" / path[String]("serviceApiKey"))
      // .in(jsonBody[ServiceUpdateApiRequest])
      .out(jsonBody[Service])
      .errorOut(emptyOutput)

}
