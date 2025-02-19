package endpoints

import cats.effect.IO

import common.models.IntrospectionRequest
import common.models.IntrospectionResponse
import common.models.IntrospectionResponse.IntrospectionResponseAction
import common.models.JsoniterSyntaticSugar.*
import io.circe.Encoder.encodeString
import io.circe.Json
import sttp.client4.http4s.Http4sBackend
import sttp.client4.jsoniter.*
import sttp.client4.UriContext
import sttp.model.MediaType

object LambdaAuthorizer {

// https://github.com/AlisProject/serverless-application/tree/c5c12ce1da576db6279b955993b8f45b72d5ea03/src/handlers/authorizer
  val uri = uri"https://api.authlete.com/api/auth/introspection"

  val scopes = List.empty[String]

  val backend = Http4sBackend.usingDefaultEmberClientBuilder[IO]()

  backend.use { backend =>
    val baseRequest = sttp
      .client4
      .basicRequest
      .auth
      .bearer("config.apiSecret")
      .contentType(MediaType.ApplicationJson)
      .post(uri)
      .body(IntrospectionRequest("token", Some(scopes), Some("Subject")).toJson)
      .response(asJson[IntrospectionResponse])
      .send(backend)

    baseRequest.flatMap(_.body match {
      case Left(ex) => IO.raiseError(new Exception(ex))
      case Right(response) =>

        response.action match {
          case None => IO.unit
          case Some(action) =>
            action match {
              case IntrospectionResponseAction.InternalServerError => IO.unit
              case IntrospectionResponseAction.BadRequest =>
                generatePolicy("subject", "Deny", "methodArn")
              case IntrospectionResponseAction.Unauthorized =>
                generatePolicy("ng", "Deny", "methodArn")
              case IntrospectionResponseAction.Forbidden =>
                generatePolicy("subject", "Deny", "methodArn")
              case IntrospectionResponseAction.Ok => generatePolicy("subject", "Allow", "methodArn")
            }

        }

        IO.pure(response)
    })
  }

  private def generatePolicy(principalId: String, effect: String, resource: String) = {
    Json.obj(
      "principalId" -> Json.fromString(principalId),
      "policyDocument" -> Json.obj(
        "Version" -> Json.fromString("2012-10-17"),
        "Statement" -> Json.arr(
          Json.obj(
            "Action"   -> Json.fromString("execute-api:Invoke"),
            "Effect"   -> Json.fromString(effect),
            "Resource" -> Json.fromString(resource)
          )
        )
      )
    )
  }.noSpaces

  private def generateAllowPolicy(principalId: String, resource: String) =
    generatePolicy(principalId, "Allow", resource)

  private def extractMethodAndPath(arn: String) = {
    val arnParts               = arn.split(":")
    val apiGatewayArnPart      = arnParts(5)
    val apiGatewayArnPartParts = apiGatewayArnPart.split("/")
    val httpMethod             = apiGatewayArnPartParts(2)
    val path                   = apiGatewayArnPartParts.drop(3).mkString("/")
    (httpMethod, path)
  }

  private def extractMethodAndPath2(arn: String) = {
    val arnElements = arn.split(":")(5).split("/")
    val httpMethod  = arnElements(2)
    val path        = arnElements(3)
    (httpMethod, path)
  }

  private def getRequiredScopes(httpMethod: String, resourcePath: String): List[String] = {
    val scopes = List("read")
    if (resourcePath == "me/unread_notification_managers" && httpMethod == "PUT") {
      scopes
    } else if (httpMethod != "GET") {
      "write" :: scopes
    } else {
      scopes
    }
  }

  import com.github.plokhotnyuk.jsoniter_scala.core._
  import com.github.plokhotnyuk.jsoniter_scala.macros._

  case class PolicyDocument(Version: String, Statement: Seq[Statement])
  case class Statement(Action: String, Effect: String, Resource: String)
  case class Policy(principalId: String, policyDocument: PolicyDocument)

  implicit val statementCodec: JsonValueCodec[Statement]           = JsonCodecMaker.make
  implicit val policyDocumentCodec: JsonValueCodec[PolicyDocument] = JsonCodecMaker.make
  implicit val policyCodec: JsonValueCodec[Policy]                 = JsonCodecMaker.make

  def generatePolicy2(principalId: String, effect: String, resource: String): Array[Byte] = {
    val statement      = Statement("execute-api:Invoke", effect, resource)
    val policyDocument = PolicyDocument("2012-10-17", Seq(statement))
    val policy         = Policy(principalId, policyDocument)
    writeToArray(policy)
  }

}
