package endpoints
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import models.*
final case class ClientExtensionEndpoints() extends BaseEndpoint {

  /** Delete a requestable scopes of a client
    * /api/client/extension/requestable_scopes/delete/{clientId} API
    */
  val clientExtensionRequestablesScopesDelete: Endpoint[Unit, String, Unit, Unit, Any] =
    endpoint.delete
      .in(
        "api" / "client" / "extension" / "requestable_scopes" / "delete" / path[
          String
        ]("clientId")
      )
      .out(emptyOutput)
      .errorOut(emptyOutput)

  /** Get the requestable scopes per client
    * /api/client/extension/requestable_scopes/get/{clientId} API
    */
  val clientExtensionRequestablesScopesGet: Endpoint[Unit, String, Unit, ClientExtensionRequestableScopesGetResponse, Any] =
    endpoint.get
      .in(
        "api" / "client" / "extension" / "requestable_scopes" / "get" / path[
          String
        ]("clientId")
      )
      .out(jsonBody[ClientExtensionRequestableScopesGetResponse])
      .errorOut(emptyOutput)

  /** Update requestable scopes of a client
    * /api/client/extension/requestable_scopes/update/{clientId} API
    */
  val clientExtensionRequestablesScopesUpdate =
    endpoint.put
      .in(
        "api" / "client" / "extension" / "requestable_scopes" / "update" / path[
          String
        ]("clientId")
      )
      .in(jsonBody[ClientExtensionRequestableScopesUpdateRequest])
      .out(jsonBody[ClientExtensionRequestableScopesUpdateResponse])
      .errorOut(emptyOutput)

}
