package endpoints

import common.models.*
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*

final case class DeviceFlowEndpoints() extends BaseEndpoint {

  /**
    * This API parses request parameters of a device authorization request and returns necessary
    * data for the authorization server implementation to process the device authorization request
    * further. /api/device/authorization API
    */
  val deviceAuthorization =
    endpoint
      .post
      .in("api" / "device" / "authorization")
      .in(jsonBody[DeviceAuthorizationRequest])
      .out(jsonBody[DeviceAuthorizationResponse])
      .errorOut(emptyOutput)

  /**
    * This API returns information about what action the authorization server should take after it
    * receives the result of end-user's decision about whether the end-user has approved or rejected
    * a client application's request. /api/device/complete API
    */
  val deviceComplete =
    endpoint
      .post
      .in("api" / "device" / "complete")
      .in(jsonBody[DeviceCompleteRequest])
      .out(jsonBody[DeviceCompleteResponse])
      .errorOut(emptyOutput)

  /**
    * The API returns information associated with a user code. /api/device/verification API
    */
  val deviceVerification =
    endpoint
      .post
      .in("api" / "device" / "verification")
      .in(jsonBody[DeviceVerificationRequest])
      .out(jsonBody[DeviceVerificationResponse])
      .errorOut(emptyOutput)

}
