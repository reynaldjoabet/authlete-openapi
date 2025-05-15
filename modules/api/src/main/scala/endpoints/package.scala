import java.util.UUID

import scala.concurrent.duration.*
import scala.util.matching.Regex

import cats.effect.kernel.Resource
import cats.effect.IO

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.CodecMakerConfig
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import common.models.*
import common.models.JsoniterSyntaticSugar.toJson
import org.http4s.ember.client.EmberClient
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.headers.`WWW-Authenticate`
import sttp.attributes.AttributeMap
import sttp.client4.http4s.Http4sBackend
import sttp.client4.httpclient.cats
import sttp.client4.httpclient.fs2
import sttp.client4.httpclient.HttpClientAsyncBackend
import sttp.client4.httpclient.HttpClientBackend
import sttp.client4.httpclient.HttpClientFutureBackend
import sttp.client4.httpclient.HttpClientSyncBackend
import sttp.client4.jsoniter.*
import sttp.client4.Backend
import sttp.client4.DefaultFutureBackend
import sttp.client4.DefaultSyncBackend
import sttp.client4.SyncBackend
import sttp.client4.UriContext
import sttp.model.headers.*
import sttp.model.MediaType
import sttp.model.StatusCode
import sttp.model.Uri
import sttp.tapir._
import sttp.tapir.{oneOfVariantValueMatcher, *}
import sttp.tapir.generic.auto._
import sttp.tapir.json.jsoniter.*
import sttp.tapir.EndpointOutput.OneOfVariant

package object endpoints {

  private val INSTANCE_URI_PREFIX = "error:instance:"

  // val endpointWithUnauthorizedResponse = endpoint
  //             .errorOut(statusCode and header[`WWW-Authenticate`](`WWW-Authenticate`(WWWAuthenticateChallenge("Bearer", Some("realm=\"example\", charset=\"UTF-8\"")))))

  import sttp.tapir.{oneOf, statusCode, Endpoint}

  final case class ErrorInfo(
      code: String,
      message: String
  ) derives ConfiguredJsonValueCodec,
        Schema
  // Codec.AsObject

  opaque type HttpError  = (StatusCode, ErrorInfo)
  opaque type AuthToken  = String
  opaque type DoorId     = String
  opaque type HealthInfo = Map[String, Any]

  private val notFoundErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] =
    statusCode.and(jsonBody[ErrorInfo].description("Not Found"))

  private val internalServerErrorErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] =
    statusCode.and(jsonBody[ErrorInfo].description("Internal Server Error"))

  private val serviceUnavailableErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] = statusCode
    .and(jsonBody[ErrorInfo].description("Service Unavailable"))

  private val internalServerErrorErrorInfoValue2: EndpointOutput[ErrorInfo] =
    statusCode(StatusCode.InternalServerError).and(
      jsonBody[ErrorInfo].description("Internal Server Error")
    )

  private val internalServerErrorErrorInfoValue3 =
    statusCode(StatusCode.InternalServerError)
      .and(jsonBody[ErrorInfo].description("Internal Server Error"))
      .and(header[Option[String]]("DPoP-Nonce"))

  private val badRequestErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] =
    statusCode.and(jsonBody[ErrorInfo].description("Bad Request"))

  private val unauthorizedErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] =
    statusCode.and(jsonBody[ErrorInfo].description("Unauthorized"))

  private val forbiddenErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] =
    statusCode.and(jsonBody[ErrorInfo].description("Forbidden"))

  private val unknownErrorInfoValue: EndpointOutput[(StatusCode, ErrorInfo)] =
    statusCode.and(jsonBody[ErrorInfo].description("unknown error"))

  object FailureVariant {

    private def statusCodeMatcher(
        statusCode: StatusCode
    ): PartialFunction[Any, Boolean] = {
      case ErrorResponse(status, _, _, _) if status == statusCode.code => true
    }

    val badRequest = oneOfVariantValueMatcher(
      StatusCode.BadRequest,
      jsonBody[ErrorResponse].description("Invalid request parameters")
    )(statusCodeMatcher(StatusCode.BadRequest))

    val internalServerError = oneOfVariantValueMatcher(
      StatusCode.InternalServerError,
      jsonBody[ErrorResponse].description("Internal server error")
    )(statusCodeMatcher(StatusCode.InternalServerError))

    val notFound = oneOfVariantValueMatcher(
      StatusCode.NotFound,
      jsonBody[ErrorResponse].description("Resource could not be found")
    )(statusCodeMatcher(StatusCode.NotFound))

    val unprocessableEntity = oneOfVariantValueMatcher(
      StatusCode.UnprocessableEntity,
      jsonBody[ErrorResponse].description("Unable to process the request")
    )(statusCodeMatcher(StatusCode.UnprocessableEntity))

    val conflict = oneOfVariantValueMatcher(
      StatusCode.Conflict,
      jsonBody[ErrorResponse].description(
        "Cannot process due to conflict with current state of the resource"
      )
    )(statusCodeMatcher(StatusCode.Conflict))

    val forbidden = oneOfVariantValueMatcher(
      StatusCode.Forbidden,
      jsonBody[ErrorResponse].description("Forbidden")
    )(statusCodeMatcher(StatusCode.Forbidden))

  }

  case class ErrorResponse(
      status: Int,
      `type`: String,
      title: String,
      detail: Option[String] = None
      // instance: String = INSTANCE_URI_PREFIX + UUID.randomUUID().toString
  ) derives ConfiguredJsonValueCodec,
        Schema

  object ErrorResponse {

    private val CamelCaseSplitRegex: Regex = "(([A-Z]?[a-z]+)|([A-Z]))".r

    def notFound(title: String = "NotFound", detail: Option[String] = None) =
      ErrorResponse(StatusCode.NotFound.code, `type` = "NotFound", title = title, detail = detail)

    def internalServerError(title: String = "InternalServerError", detail: Option[String] = None) =
      ErrorResponse(
        StatusCode.InternalServerError.code,
        `type` = "InternalServerError",
        title = title,
        detail = detail
      )

    def badRequest(title: String = "BadRequest", detail: Option[String] = None) =
      ErrorResponse(
        StatusCode.BadRequest.code,
        `type` = title,
        title = title,
        detail = detail
      )

    def unprocessableEntity(title: String = "UnprocessableEntity", detail: Option[String] = None) =
      ErrorResponse(
        StatusCode.UnprocessableEntity.code,
        `type` = title,
        title = title,
        detail = detail
      )

    def conflict(title: String = "Conflict", detail: Option[String] = None) =
      ErrorResponse(
        StatusCode.Conflict.code,
        `type` = title,
        title = title,
        detail = detail
      )

  }

  import sttp.client4.basicRequest

  val url: Uri = sttp.model.Uri("", 23)

  private val baseRequest = basicRequest
    .auth
    .bearer("config.apiSecret")
    .contentType(MediaType.ApplicationJson)
    .readTimeout(10.seconds)
  // .readTimeout(config.requestTimeout)

  private val baseUri = Uri("https", "config.host", 8090) // config.port)

  def postRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicRequest
      .post(uri.withPath("api", "authorization"))
      .body(body.toJson)
      .contentType(MediaType.ApplicationJson)
      .response(asJson[R])

  def putRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicRequest
      .put(uri.withPath("api", "authorization"))
      .body(body.toJson)
      .contentType(MediaType.ApplicationJson)
      .response(asJson[R])

  def postRequestForm[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicRequest
      .post(uri)
      .body(body.toJson)
      .response(asJson[R])
      .contentType(MediaType.ApplicationXWwwFormUrlencoded)

  def getRequest[R: JsonValueCodec](uri: Uri) =
    basicRequest.get(uri).response(asJson[R])

  def deleteRequest[R: JsonValueCodec](uri: Uri) =
    basicRequest.get(uri).response(asJson[R])

  val request = basicRequest
    .get(url)
    .response(asJson[AuthorizationFailResponse])
    .body(
      AuthorizationFailRequest(
        "ticket",
        AuthorizationFailRequest.AuthorizationFailRequestReason.DENIED
      ).toJson
    )

  val backend = Http4sBackend.usingDefaultEmberClientBuilder[IO]()

  backend.use { backend =>
    request
      .send(backend)
      // .flatTap(resp =>
      //         logger.info(s"AuthorizationFail response  with action ${resp.action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      //       )
      .flatMap { resp =>
        resp.body match {
          case Left(error)  => ???
          case Right(value) => ???
        }
      }

    request.mapResponse {
      case Left(ex)     => ???
      case Right(value) => ???
    }

    request
      .send(backend)
      .flatMap(
        _.body
          .fold(
            _ => ???,
            resp => {
              resp.action match {
                case None        => ???
                case Some(value) => ???
              }
            }
          )
      )

  }

  EmberClientBuilder.default[IO].build.map(Http4sBackend.usingClient(_))

  sealed trait Block
  object Block {

    sealed trait Text extends Block {
      def text: String
    }

    object Text {

      final case class Markdown(text: String) extends Text
      final case class Plain(text: String)    extends Text

      final case class Section(text: Text) extends Block

    }

  }

  final case class MsgPayload(
      text: String,
      blocks: Vector[Block]
  ) derives ConfiguredJsonValueCodec,
        Schema

  trait SlackClient[F[_]] {
    def respondTo(responseUrl: String, response: MsgPayload): F[Unit]
  }

  final class SttpSlackClient(using backend: Backend[IO]) extends SlackClient[IO] {

    def respondTo(responseUrl: String, response: MsgPayload): IO[Unit] =
      basicRequest
        .post(uri"$responseUrl")
        .body(response.toJson)
        .contentType("application/json")
        .send(backend)
        .void

  }

}
