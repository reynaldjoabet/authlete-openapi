package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class FederationEndpoints() extends BaseEndpoint {

  /**
    * This API gathers the federation configuration about a service. /api/federation/configuration
    * API
    */
  val federationConfiguration =
    endpoint
      .post
      .in("api" / "federation" / "configuration")
      // .in(jsonBody[FederationConfigurationRequest])
      .out(jsonBody[FederationConfigurationResponse])
      .errorOut(emptyOutput)

  /**
    * The Authlete API is for implementations of the federation registration endpoint that accepts
    * "explicit client registration". /api/federation/registration API
    */
  val federationRegistration =
    endpoint
      .post
      .in("api" / "federation" / "registration")
      .in(jsonBody[FederationRegistrationRequest])
      .out(jsonBody[FederationRegistrationResponse])
      .errorOut(emptyOutput)

}
