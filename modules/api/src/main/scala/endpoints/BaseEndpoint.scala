package endpoints

import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.jsoniter.*

open class BaseEndpoint {

  // val baseEndpoint: Endpoint[Unit, Unit, Throwable, Unit, Any] = endpoint
  //   .errorOut(statusCode.and(plainBody[String]))
  //   .mapErrorOut[Throwable](HttpError.decode(_))(HttpError.encode)
  val baseEndpoint: Endpoint[Unit, Unit, (StatusCode, String), Unit, Any] =
    endpoint.errorOut(statusCode.and(plainBody[String]))

  // val secureBaseEndpoint: Endpoint[String, Unit, Throwable, Unit, Any] =
  //     baseEndpoint.securityIn(auth.bearer[String]())

  // val secureBaseEndpoint2: Endpoint[String, Unit, Throwable, Unit, Any] =
  //           baseEndpoint.securityIn(auth.basic[String]())
}
