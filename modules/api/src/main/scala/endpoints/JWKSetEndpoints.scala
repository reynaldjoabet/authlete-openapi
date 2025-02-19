package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class JWKSetEndpoints() extends BaseEndpoint {

  /**
    * This API gathers JWK Set information for a service so that its client applications can verify
    * signatures by the service and encrypt their requests to the service. /api/service/jwks/get API
    */

  val serviceJwksGet =
    endpoint
      .get
      .in("api" / "service" / "jwks" / "get")
      .in(query[Option[Boolean]]("includePrivateKeys"))
      .in(query[Option[Boolean]]("pretty"))
      // .out(jsonBody[ServiceJwksGetResponse])
      .errorOut(emptyOutput)

}
