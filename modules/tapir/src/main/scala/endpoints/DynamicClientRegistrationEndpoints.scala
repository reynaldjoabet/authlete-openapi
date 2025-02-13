package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*
final case class DynamicClientRegistrationEndpoints() extends BaseEndpoint {

  /** Register a client. This API is supposed to be used to implement a client
    * registration endpoint that complies with RFC 7591 (OAuth 2.0 Dynamic
    * Client Registration Protocol). /api/client/registration API
    */
  val clientRegistration =
    endpoint.post
      .in("api" / "client" / "registration")
      .in(jsonBody[ClientRegistrationRequest])
      .out(jsonBody[ClientRegistrationResponse])
      .errorOut(emptyOutput)

  /** Delete a dynamically registered client. This API is supposed to be used to
    * implement a client registration management endpoint that complies with RFC
    * 7592. /api/client/registration/delete API
    */
  val clientRegistrationDelete =
    endpoint.post
      .in("api" / "client" / "registration" / "delete")
      .in(jsonBody[ClientRegistrationDeleteRequest])
      .out(jsonBody[ClientRegistrationDeleteResponse])
      .errorOut(emptyOutput)

  /** Get a dynamically registered client. This API is supposed to be used to
    * implement a client registration management endpoint that complies with RFC
    * 7592. /api/client/registration/get API
    */
  val clientRegistrationGet =
    endpoint.post
      .in("api" / "client" / "registration" / "get")
      .in(jsonBody[ClientRegistrationGetRequest])
      .out(jsonBody[ClientRegistrationGetResponse])
      .errorOut(emptyOutput)

  /** Update a dynamically registered client. This API is supposed to be used to
    * implement a client registration management endpoint that complies with RFC
    * 7592. /api/client/registration/update API
    */
  val clientRegistrationUpdate =
    endpoint.post
      .in("api" / "client" / "registration" / "update")
      // .in(jsonBody[ClientRegistrationUpdateRequest])
      .out(jsonBody[ClientRegistrationUpdateResponse])
      .errorOut(emptyOutput)

case class ClientRegistrationApiRequest(
        clientRegistrationRequest: ClientRegistrationRequest
    )
case class ClientRegistrationDeleteApiRequest(
        clientRegistrationDeleteRequest: ClientRegistrationDeleteRequest
    )
case class ClientRegistrationGetApiRequest(
        clientRegistrationGetRequest: ClientRegistrationGetRequest
    )
    // case class ClientRegistrationUpdateApiRequest(clientRegistrationUpdateRequest: ClientRegistrationUpdateRequest)
}
