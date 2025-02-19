package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class HskEndpoints() extends BaseEndpoint {

  /**
    * /api/hsk/create API
    */
  val hskCreate =
    endpoint
      .post
      .in("api" / "hsk" / "create")
      .in(jsonBody[HskCreateRequest])
      .out(jsonBody[HskCreateResponse])
      .errorOut(emptyOutput)

  /**
    * /api/hsk/delete/{handle} API
    */
  val hskDelete =
    endpoint
      .delete
      .in("api" / "hsk" / "delete" / path[String]("handle"))
      .out(jsonBody[HskDeleteResponse])
      .errorOut(emptyOutput)

  /**
    * /api/hsk/get/{handle} API
    */
  val hskGet =
    endpoint
      .get
      .in("api" / "hsk" / "get" / path[String]("handle"))
      .out(jsonBody[HskGetResponse])
      .errorOut(emptyOutput)

  /**
    * /api/hsk/get/list API
    */
  val hskGetList =
    endpoint
      .get
      .in("api" / "hsk" / "get" / "list")
      .out(jsonBody[HskGetListResponse])
      .errorOut(emptyOutput)

}
