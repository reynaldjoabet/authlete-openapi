package services

import scala.concurrent.duration.DurationInt

import cats.effect.kernel.Async
import cats.effect.kernel.Resource
import cats.effect.syntax.all.*
import cats.syntax.all.*
import cats.syntax.applicativeError.*

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import common.models.*
import common.models.JsoniterSyntaticSugar.toJson
import configs.AuthleteConfig
import org.typelevel.log4cats.Logger
import sttp.client4.*
import sttp.client4.http4s.*
import sttp.client4.jsoniter.*
import sttp.model.MediaType
import sttp.model.StatusCode
import sttp.model.Uri

final case class AuthleteServiceImpl[F[_]: Async](
    backend: Http4sBackend[F],
    config: AuthleteConfig,
    logger: Logger[F]
) extends AuthleteService[F] {

  private val basicReq = basicRequest
    .auth
    // .basic(config.apiKey, config.apiSecret)// V3 API requires an access token, not a key and secret
    .bearer(config.auth)
    .contentType(MediaType.ApplicationJson)
    .readTimeout(config.requestTimeout)

  private val baseUri = Uri(config.baseUrl)

  private[services] def postRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicReq.post(uri).body(body.toJson).contentType(MediaType.ApplicationJson).response(asJson[R])

  private[services] def postRequestWithUnitResponse[B: JsonValueCodec](uri: Uri, body: B) =
    basicReq.post(uri).body(body.toJson).contentType(MediaType.ApplicationJson).response(ignore)

  private[services] def postRequest[R: JsonValueCodec](uri: Uri) =
    basicReq.post(uri).response(asJson[R])

  private[services] def postRequestWithUnitResponse(uri: Uri) =
    basicReq.post(uri).response(ignore)

  private[services] def putRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicReq.put(uri).body(body.toJson).contentType(MediaType.ApplicationJson).response(asJson[R])

  // If you set the body as a Map[String, String] or Seq[(String, String)], it will be encoded as form-data (as if a web form with the given values was submitted)
  private[services] def postRequestForm[R: JsonValueCodec](uri: Uri, body: Map[String, String]) =
    basicReq
      .post(uri)
      .body(body)
      .response(asJson[R])
      .contentType(MediaType.ApplicationXWwwFormUrlencoded)

  private[services] def getRequest[R: JsonValueCodec](uri: Uri) =
    basicReq.get(uri).response(asJson[R])

  private[services] def deleteRequestWithUnitResponse(uri: Uri) =
    basicReq.delete(uri).response(ignore)

  private[services] def deleteRequest[R: JsonValueCodec](uri: Uri) =
    basicReq.delete(uri).response(asJson[R])

  override def authorization(
      body: AuthorizationRequest
  ): F[AuthorizationResponse] = {

    // s"/api/${config.apiKey}/auth/authorization")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "authorization"))

    postRequest[AuthorizationRequest, AuthorizationResponse](uri, body)
      .send(backend)
      .flatMap {
        res =>
          res.code match {
            case StatusCode.Ok => res.body.fold(???, body => body) // [ResponseException[String, Exception]]
          }
        ???
      }
      // .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Authorization response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def authorization(
      body: Map[String, String]
  ): F[AuthorizationResponse] = {

    // s"/api/${config.apiKey}/auth/authorization")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "authorization"))

    postRequest[AuthorizationResponse](uri)
      .body(body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Authorization response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def authorizationFail(
      body: AuthorizationFailRequest
  ): F[AuthorizationFailResponse] = {

//s"/api/${config.apiKey}/auth/authorization/fail")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "authorization", "fail"))

    postRequest[AuthorizationFailRequest, AuthorizationFailResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"AuthorizationFail response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def authorizationIssue(
      body: AuthorizationIssueRequest
  ): F[AuthorizationIssueResponse] = {

    // s"/api/${config.apiKey}/auth/authorization/issue"
    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "authorization", "issue"))
    postRequest[AuthorizationIssueRequest, AuthorizationIssueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"AuthorizationIssue response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def token(body: TokenRequest): F[TokenResponse] = {

    // s"/api/${config.apiKey}/auth/token")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token"))

    postRequest[TokenRequest, TokenResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Token response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def tokenCreate(body: TokenCreateRequest): F[TokenCreateResponse] = {

//s"/api/${config.apiKey}/auth/token/create")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token", "create"))
    postRequest[TokenCreateRequest, TokenCreateResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"TokenCreate response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def tokenDelete(token: String): F[Unit] = {

    // s"/api/${config.apiKey}/auth/token/delete/$token"

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token", "delete", token))

    deleteRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ => logger.info(s"Token deleted successfully"))

  }

  override def tokenFail(body: TokenFailRequest): F[TokenFailResponse] = {

    // s"/api/${config.apiKey}/auth/token/fail")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token", "fail"))

    postRequest[TokenFailRequest, TokenFailResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"TokenFail response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def tokenIssue(body: TokenIssueRequest): F[TokenIssueResponse] = {

    // s"/api/${config.apiKey}/auth/token/issue")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token", "issue"))
    postRequest[TokenIssueRequest, TokenIssueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"TokenIssue response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def tokenRevoke(body: TokenRevokeRequest): F[TokenRevokeResponse] = {

    // s"/api/${config.apiKey}/auth/token/revoke")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token", "revoke"))

    postRequest[TokenRevokeRequest, TokenRevokeResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"TokenRevoke response  with result code ${resp.resultCode} and message ${resp
            .resultMessage}")
      )
  }

  override def tokenUpdate(body: TokenUpdateRequest): F[TokenUpdateResponse] = {

    // s"/api/${config.apiKey}/auth/token/update")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "token", "update"))
    postRequest[TokenUpdateRequest, TokenUpdateResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"TokenUpdate response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def getTokenList(): F[TokenListResponse] =
    getTokenList(None, None, 0, 0, false, TokenStatus.ALL)

  override def getTokenList(tokenStatus: TokenStatus): F[TokenListResponse] =
    getTokenList(None, None, 0, 0, false, tokenStatus)

  override def getTokenList(
      clientIdentifier: String,
      subject: String
  ): F[TokenListResponse] =
    getTokenList(
      Some(clientIdentifier),
      Some(subject),
      0,
      0,
      false,
      TokenStatus.ALL
    )

  override def getTokenList(
      clientIdentifier: String,
      subject: String,
      tokenStatus: TokenStatus
  ): F[TokenListResponse] =
    getTokenList(
      Some(clientIdentifier),
      Some(subject),
      0,
      0,
      false,
      tokenStatus
    )

  override def getTokenList(start: Int, end: Int): F[TokenListResponse] =
    getTokenList(None, None, start, end, true, TokenStatus.ALL)

  override def getTokenList(
      start: Int,
      end: Int,
      tokenStatus: TokenStatus
  ): F[TokenListResponse] =
    getTokenList(None, None, start, end, true, tokenStatus)

  override def getTokenList(
      clientIdentifier: String,
      subject: String,
      start: Int,
      end: Int
  ): F[TokenListResponse] =
    getTokenList(
      Some(clientIdentifier),
      Some(subject),
      start,
      end,
      true,
      TokenStatus.ALL
    )

  override def getTokenList(
      clientIdentifier: Option[String],
      subject: Option[String],
      start: Int,
      end: Int,
      rangeGiven: Boolean,
      tokenStatus: TokenStatus
  ): F[TokenListResponse] = {

    val queryParams = Map(
      "clientIdentifier" -> clientIdentifier,
      "subject"          -> subject,
      "tokenStatus"      -> tokenStatus.toString
    ).collect { case (k, Some(v)) => k -> v }
      .concat(
        if (rangeGiven)
          Map(
            "start" -> start.toString,
            "end"   -> end.toString
          )
        else Map.empty[String, String]
      )

    // baseUri
    //   .withPath(
    //     Path.unsafeFromString(s"/api/${config.apiKey}/auth/token/list")
    //   )
    //   .addParams(queryParams)//

    val uri = baseUri
      .withPath(List("api", config.apiKey, "auth", "token", "list"))
      .addParams(queryParams)
    getRequest[TokenListResponse](uri)
      .header("Accept", "application/json")
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved token list"))
  }

  override def revocation(body: RevocationRequest): F[RevocationResponse] = {

    // s"/api/${config.apiKey}/auth/revocation")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "revocation"))
    postRequest[RevocationRequest, RevocationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Revocation response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def userinfo(body: UserInfoRequest): F[UserInfoResponse] = {

    // s"/api/${config.apiKey}/auth/userinfo")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "userinfo"))

    postRequest[UserInfoRequest, UserInfoResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"UserInfo response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def userinfoIssue(
      body: UserInfoIssueRequest
  ): F[UserInfoIssueResponse] = {

    // api/${config.apiKey}/auth/userinfo/issue")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "userinfo", "issue"))
    postRequest[UserInfoIssueRequest, UserInfoIssueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"UserInfoIssue response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def introspection(
      body: IntrospectionRequest
  ): F[IntrospectionResponse] = {

    // "/api/${config.apiKey}/auth/introspection")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "introspection"))
    postRequest[IntrospectionRequest, IntrospectionResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Introspection response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def standardIntrospection(
      body: StandardIntrospectionRequest
  ): F[StandardIntrospectionResponse] = {

    // s"/api/${config.apiKey}/auth/introspection/standard"

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "introspection", "standard"))
    postRequest[StandardIntrospectionRequest, StandardIntrospectionResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"StandardIntrospection response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def createService(service: Service): F[Service] = {

    // s"/api/service/create"))

    val uri = baseUri.withPath(List("api", "service", "create"))
    postRequest[Service, Service](uri, service)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info(s"Service created successfully: $resp"))
  }

  override def deleteService(): F[Unit] = {

    // s"/api/${config.apiKey}/service/delete")

    val uri = baseUri.withPath(List("api", config.apiKey, "service", "delete"))
    deleteRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ => logger.info(s"Service deleted successfully "))
  }

  override def getService(): F[Service] = {

    // s"/api/${config.apiKey}/service/get")

    val uri = baseUri.withPath(List("api", config.apiKey, "service", "get"))
    getRequest[Service](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully retrieved service with service id ${resp
            .number} and service name ${resp.serviceName}")
      )
  }

  override def getServiceList(): F[ServiceListResponse] =
    getServiceList(0, 0, false)

  override def getServiceList(start: Int, end: Int): F[ServiceListResponse] =
    getServiceList(start, end, true)

  override def getServiceList(
      start: Int,
      end: Int,
      rangeGiven: Boolean
  ): F[ServiceListResponse] = {
    val queryParams =
      if (rangeGiven)
        Map(
          "start" -> start.toString,
          "end"   -> end.toString
        )
      else Map.empty[String, String]

//s"/api/service/get/list"))

    val uri = baseUri.withPath(List("api", "service", "get", "list")).addParams(queryParams)

    getRequest[ServiceListResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved service list"))
  }

  override def updateService(service: Service): F[Service] = {

    // "/api/${config.apiKey}/service/update")

    val uri = baseUri.withPath(List("api", config.apiKey, "service", "update"))
    postRequest[Service, Service](uri, service)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully updated service with service id ${resp
            .number} and service name ${resp.serviceName}")
      )
  }

  override def getServiceJwks(): F[String] = {

    // "/api/${config.apiKey}/service/jwks/get")

    val uri = baseUri.withPath(List("api", config.apiKey, "service", "jwks", "get"))
    getRequest[String](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved service jwks"))
  }

  override def getServiceJwks(
      pretty: Boolean,
      includePrivateKeys: Boolean
  ): F[String] = {

    val queryParams = Map(
      "pretty"             -> pretty.toString,
      "includePrivateKeys" -> includePrivateKeys.toString
    )
    // s"/api/${config.apiKey}/service/jwks/get")

    val uri = baseUri
      .withPath(List("api", config.apiKey, "service", "jwks", "get"))
      .addParams(queryParams)
    getRequest[String](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved service jwks"))
  }

  override def getServiceConfiguration(): F[String] = {

    // "/api/${config.apiKey}/service/configuration")

    val uri = baseUri.withPath(List("api", config.apiKey, "service", "configuration"))
    getRequest[String](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved service configuration"))
  }

  override def getServiceConfiguration(pretty: Boolean): F[String] = {

    val queryParams = Map(
      "pretty" -> pretty.toString
    )
    // "/api/${config.apiKey}/service/configuration")

    val uri = baseUri
      .withPath(List("api", config.apiKey, "service", "configuration"))
      .addParams(queryParams)
    getRequest[String](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved service configuration"))
  }

  override def getServiceConfiguration(
      body: ServiceConfigurationRequest
  ): F[String] = {

    // "/api/${config.apiKey}/service/configuration")

    val uri = baseUri.withPath(List("api", config.apiKey, "service", "configuration"))
    postRequest[ServiceConfigurationRequest, String](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved service configuration"))
  }

  override def createClient(body: Client): F[Client] = {
    // s"/api/${config.apiKey}/client/create")

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "create"))
    postRequest[Client, Client](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully created client with client id ${resp
            .clientId} and client name ${resp.clientName}")
      )
  }

  override def dynamicClientRegister(
      body: ClientRegistrationRequest
  ): F[ClientRegistrationResponse] = {

    // s"/api/${config.apiKey}/client/registration"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "registration"))
    postRequest[ClientRegistrationRequest, ClientRegistrationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"ClientRegistration response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def dynamicClientGet(
      body: ClientRegistrationRequest
  ): F[ClientRegistrationResponse] = {
    // s"/api/${config.apiKey}/client/registration/get"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "registration", "get"))
    postRequest[ClientRegistrationRequest, ClientRegistrationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"ClientRegistration response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def dynamicClientUpdate(
      body: ClientRegistrationRequest
  ): F[ClientRegistrationResponse] = {

    // s"/api/${config.apiKey}/client/registration/update"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "registration", "update"))
    postRequest[ClientRegistrationRequest, ClientRegistrationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"ClientRegistration updated:  response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def dynamicClientDelete(
      body: ClientRegistrationRequest
  ): F[ClientRegistrationResponse] = {

    // s"/api/${config.apiKey}/client/registration/delete"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "registration", "delete"))
    postRequest[ClientRegistrationRequest, ClientRegistrationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"ClientRegistration deleted:  response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def deleteClient(clientId: Long): F[Unit] =
    deleteClient(clientId.toString)

  override def deleteClient(clientId: String): F[Unit] = {

    // s"/api/${config.apiKey}/client/delete/$clientId"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "delete", clientId))

    deleteRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ => logger.info(s"Client deleted successfully"))
  }

  override def getClient(clientId: Long): F[Client] =
    getClient(clientId.toString)

  override def getClient(clientId: String): F[Client] = {

    // s"/api/${config.apiKey}/client/get/$clientId"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "get", clientId))
    getRequest[Client](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully retrieved client with client id ${resp
            .clientId} and client name ${resp.clientName}")
      )
  }

  override def getClientList(): F[ClientListResponse] =
    getClientList(None, 0, 0, false)

  override def getClientList(developer: String): F[ClientListResponse] =
    getClientList(Some(developer), 0, 0, false)

  override def getClientList(start: Int, end: Int): F[ClientListResponse] =
    getClientList(None, start, end, true)

  override def getClientList(
      developer: String,
      start: Int,
      end: Int
  ): F[ClientListResponse] =
    getClientList(Some(developer), start, end, true)

  override def getClientList(
      developer: Option[String],
      start: Int,
      end: Int,
      rangeGiven: Boolean
  ): F[ClientListResponse] = {
    val queryParams = Map(
      "developer" -> developer
    ).collect { case (k, Some(v)) => k -> v }
      .concat(
        if (rangeGiven)
          Map(
            "start" -> start.toString,
            "end"   -> end.toString
          )
        else Map.empty[String, String]
      )

    val uri = baseUri
      .withPath(List("api", config.apiKey, "client", "get", "list"))
      .addParams(queryParams)
    getRequest[ClientListResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("successfully retrieved client list"))
  }

  override def updateClient(body: Client): F[Client] = {
//s"/api/${config.apiKey}/client/update")
    val uri = baseUri.withPath(List("api", config.apiKey, "client", "update"))
    postRequest[Client, Client](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully updated client with client id ${resp
            .clientId} and client name ${resp.clientName}")
      )
  }

  override def getRequestableScopes(clientId: Long): F[List[String]] = {

    // s"/api/${config.apiKey}/client/extension/requestable_scopes/get/$clientId"

    val uri = baseUri.withPath(
      List(
        "api",
        config.apiKey,
        "client",
        "extension",
        "requestable_scopes",
        "get",
        clientId.toString
      )
    )
    getRequest[List[String]](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully retrieved requestable scopes for client $clientId")
      )

  }

  override def setRequestableScopes(
      clientId: Long,
      scopes: List[String]
  ): F[List[String]] = {

    // s"/api/${config.apiKey}/client/extension/requestable_scopes/set/$clientId"

    // withEntity("requestableScopes" -> scopes)

    val uri = baseUri.withPath(
      List(
        "api",
        config.apiKey,
        "client",
        "extension",
        "requestable_scopes",
        "set",
        clientId.toString
      )
    )

    // basicReq

    postRequest[List[String], List[String]](uri, scopes)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info(s"Successfully set requestable scopes for client $clientId"))
  }

  override def deleteRequestableScopes(clientId: Long): F[Unit] = {

    val uri = baseUri.withPath(
      List(
        "api",
        config.apiKey,
        "client",
        "extension",
        "requestable_scopes",
        "delete",
        clientId.toString
      )
    )
    deleteRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ => logger.info(s"Requestable scopes deleted successfully"))
  }

  override def getGrantedScopes(
      clientId: Long,
      subject: String
  ): F[GrantedScopesGetResponse] = {

    // s"/api/${config.apiKey}/client/extension/granted_scopes/get/$clientId"

    val uri = baseUri.withPath(
      List("api", config.apiKey, "client", "extension", "granted_scopes", "get", clientId.toString)
    )
    getRequest[GrantedScopesGetResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(
          s"Successfully retrieved granted scopes for client $clientId and subject $subject"
        )
      )
  }

  override def deleteGrantedScopes(clientId: Long, subject: String): F[Unit] = {
    // s"/api/${config.apiKey}/client/extension/granted_scopes/delete/$clientId"

    val uri = baseUri.withPath(
      List(
        "api",
        config.apiKey,
        "client",
        "extension",
        "granted_scopes",
        "delete",
        clientId.toString
      )
    )
    deleteRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ => logger.info(s"Granted scopes deleted successfully"))
  }

  override def deleteClientAuthorization(
      clientId: Long,
      subject: String
  ): F[Unit] = {

    // s"/api/${config.apiKey}/client/authorization/delete/$clientId"

    // withEntity("subject" -> subject)

    val uri = baseUri.withPath(
      List("api", config.apiKey, "client", "authorization", "delete", clientId.toString)
    )
    deleteRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ =>
        logger.info(
          s"Client authorization deleted successfully for client $clientId and subject $subject"
        )
      )

  }

  override def getClientAuthorizationList(
      request: ClientAuthorizationGetListRequest
  ): F[AuthorizedClientListResponse] = {

    // s"/api/${config.apiKey}/client/authorization/get/list"

    val uri = baseUri.withPath(List("api", config.apiKey, "client", "authorization", "get", "list"))
    postRequest[ClientAuthorizationGetListRequest, AuthorizedClientListResponse](uri, request)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(
          s"Successfully retrieved client authorization list : ${resp.clients}"
        )
      )
  }

  override def updateClientAuthorization(
      clientId: Long,
      body: ClientAuthorizationUpdateRequest
  ): F[Unit] = {

    // s"/api/${config.apiKey}/client/authorization/update/$clientId"

    val uri = baseUri.withPath(
      List("api", config.apiKey, "client", "authorization", "update", clientId.toString)
    )
    postRequestWithUnitResponse[ClientAuthorizationUpdateRequest](uri, body)
      .send(backend)
      .map(_.body)
      .flatTap(_ => logger.info(s"Client authorization updated successfully for client $clientId"))
  }

  override def refreshClientSecret(
      clientId: Long
  ): F[ClientSecretRefreshResponse] =
    refreshClientSecret(clientId.toString)

  override def refreshClientSecret(
      clientIdentifier: String
  ): F[ClientSecretRefreshResponse] = {

    // s"/api/${config.apiKey}/client/refresh/$clientIdentifier"

    // check this
    val uri = baseUri.withPath(List("api", config.apiKey, "client", "refresh", clientIdentifier))
    postRequest[ClientSecretRefreshResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully refreshed client secret with result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def updateClientSecret(
      clientId: Long,
      clientSecret: String
  ): F[ClientSecretUpdateResponse] =
    updateClientSecret(clientId.toString, clientSecret)

  override def updateClientSecret(
      clientIdentifier: String,
      clientSecret: String
  ): F[ClientSecretUpdateResponse] = {

    // s"/api/${config.apiKey}/client/secret/update/$clientIdentifier"

    val uri =
      baseUri.withPath(List("api", config.apiKey, "client", "secret", "update", clientIdentifier))
    postRequest[String, ClientSecretUpdateResponse](uri, clientSecret)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully updated client secret with result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def verifyJose(body: JoseVerifyRequest): F[JoseVerifyResponse] = {

    // s"/api/${config.apiKey}/jose/verify")

    val uri = baseUri.withPath(List("api", config.apiKey, "jose", "verify"))
    postRequest[JoseVerifyRequest, JoseVerifyResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Successfully verified JOSE with result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def backchannelAuthentication(
      body: BackchannelAuthenticationRequest
  ): F[BackchannelAuthenticationResponse] = {

    // "/api/${config.apiKey}/backchannel/authentication")

    val uri = baseUri.withPath(List("api", config.apiKey, "backchannel", "authentication"))
    postRequest[BackchannelAuthenticationRequest, BackchannelAuthenticationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"BackchannelAuthentication response with action ${resp
            .action},  result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def backchannelAuthenticationIssue(
      body: BackchannelAuthenticationIssueRequest
  ): F[BackchannelAuthenticationIssueResponse] = {

    // s"/api/${config.apiKey}/backchannel/authentication/issue"

    val uri = baseUri.withPath(List("api", config.apiKey, "backchannel", "authentication", "issue"))
    postRequest[BackchannelAuthenticationIssueRequest, BackchannelAuthenticationIssueResponse](
      uri,
      body
    ).send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"BackchannelAuthenticationIssue response with  action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def backchannelAuthenticationFail(
      body: BackchannelAuthenticationFailRequest
  ): F[BackchannelAuthenticationFailResponse] = {

    // s"/api/${config.apiKey}/backchannel/authentication/fail"

    val uri = baseUri.withPath(List("api", config.apiKey, "backchannel", "authentication", "fail"))
    postRequest[BackchannelAuthenticationFailRequest, BackchannelAuthenticationFailResponse](
      uri,
      body
    ).send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"BackchannelAuthenticationFail response with  action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def backchannelAuthenticationComplete(
      body: BackchannelAuthenticationCompleteRequest
  ): F[BackchannelAuthenticationCompleteResponse] = {

    // s"/api/${config.apiKey}/backchannel/authentication/complete"

    val uri =
      baseUri.withPath(List("api", config.apiKey, "backchannel", "authentication", "complete"))
    postRequest[
      BackchannelAuthenticationCompleteRequest,
      BackchannelAuthenticationCompleteResponse
    ](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"BackchannelAuthenticationComplete response with  action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )

  }

  override def deviceAuthorization(
      body: DeviceAuthorizationRequest
  ): F[DeviceAuthorizationResponse] = {

    // "/api/${config.apiKey}/device/authorization")

    val uri = baseUri.withPath(List("api", config.apiKey, "device", "authorization"))
    postRequest[DeviceAuthorizationRequest, DeviceAuthorizationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"DeviceAuthorization response with  action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def deviceComplete(
      body: DeviceCompleteRequest
  ): F[DeviceCompleteResponse] = {

    // "/api/${config.apiKey}/device/complete")

    val uri = baseUri.withPath(List("api", config.apiKey, "device", "complete"))
    postRequest[DeviceCompleteRequest, DeviceCompleteResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"DeviceComplete response with  action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def deviceVerification(
      body: DeviceVerificationRequest
  ): F[DeviceVerificationResponse] = {

    // "/api/${config.apiKey}/device/verification")

    val uri = baseUri.withPath(List("api", config.apiKey, "device", "verification"))
    postRequest[DeviceVerificationRequest, DeviceVerificationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"DeviceVerification response with  action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def pushAuthorizationRequest(
      body: PushedAuthReqRequest
  ): F[PushedAuthReqResponse] = {

    // s"/api/${config.apiKey}/pushed_auth_req")

    val uri = baseUri.withPath(List("api", config.apiKey, "pushed_auth_req"))
    postRequest[PushedAuthReqRequest, PushedAuthReqResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"PushedAuthReq response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def hskCreate(body: HskCreateRequest): F[HskResponse] = {

    // s"/api/${config.apiKey}/hsk/create")

    val uri = baseUri.withPath(List("api", config.apiKey, "hsk", "create"))
    postRequest[HskCreateRequest, HskResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Hsk create response with  action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def hskDelete(handle: String): F[HskResponse] = {

    // "/api/${config.apiKey}/hsk/delete/$handle")

    val uri = baseUri.withPath(List("api", config.apiKey, "hsk", "delete", handle))

    deleteRequest[HskResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Hsk delete response with  action ${resp.action}, result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def hskGet(handle: String): F[HskResponse] = {

    // s"/api/${config.apiKey}/hsk/get/$handle")

    val uri = baseUri.withPath(List("api", config.apiKey, "hsk", "get", handle))
    getRequest[HskResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"Hsk get response with  action ${resp.action}, result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def hskGetList(): F[HskListResponse] = {

    // s"/api/${config.apiKey}/hsk/get/list")

    val uri = baseUri.withPath(List("api", config.apiKey, "hsk", "get", "list"))
    getRequest[HskListResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info("Successfully retrieved HSK list"))

  }

  override def echo(parameters: Map[String, String]): F[Map[String, String]] = {

    val uri = baseUri.withPath(List("api", config.apiKey, "echo")).addParams(parameters)

    getRequest[Map[String, String]](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp => logger.info(s"Echo response"))

  }

  override def gm(body: GMRequest): F[GMResponse] = {

    // s"/api/${config.apiKey}/gm")

    val uri = baseUri.withPath(List("api", config.apiKey, "gm"))
    postRequest[GMRequest, GMResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(
          s"GM response  with action ${resp.action} ,result code ${resp
              .resultCode} and message ${resp.resultMessage}"
        )
      )
  }

  override def updateClientLockFlag(
      clientIdentifier: String,
      clientLocked: Boolean
  ): F[Unit] = {

    // "/api/${config.apiKey}/client/lock/$clientIdentifier/$clientLocked"

    val uri = baseUri.withPath(
      List("api", config.apiKey, "client", "lock", clientIdentifier, clientLocked.toString)
    )
    postRequestWithUnitResponse(uri)
      .send(backend)
      .map(_.body)
      .flatTap(_ =>
        logger.info(s"Client lock flag updated successfully: for client $clientIdentifier")
      )

  }

  override def federationConfiguration(
      body: FederationConfigurationRequest
  ): F[FederationConfigurationResponse] = {

    // s"/api/${config.apiKey}/federation/configuration")
    val uri = baseUri.withPath(List("api", config.apiKey, "federation", "configuration"))
    postRequest[FederationConfigurationRequest, FederationConfigurationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"FederationConfiguration response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def federationRegistration(
      body: FederationRegistrationRequest
  ): F[FederationRegistrationResponse] = {
//s"/api/${config.apiKey}/federation/registration")

    val uri = baseUri.withPath(List("api", config.apiKey, "federation", "registration"))
    postRequest[FederationRegistrationRequest, FederationRegistrationResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"FederationRegistration response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialIssuerMetadata(
      body: CredentialIssuerMetadataRequest
  ): F[CredentialIssuerMetadataResponse] = {

    // s"/api/${config.apiKey}/credential/issuer/metadata")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "issuer", "metadata"))
    postRequest[CredentialIssuerMetadataRequest, CredentialIssuerMetadataResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialIssueMetadata response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialJwtIssuerMetadata(
      body: CredentialJwtIssuerMetadataRequest
  ): F[CredentialJwtIssuerMetadataResponse] = {

    // s"/api/${config.apiKey}/credential/jwt/issuer/metadata"

    val uri =
      baseUri.withPath(List("api", config.apiKey, "credential", "jwt", "issuer", "metadata"))
    postRequest[CredentialJwtIssuerMetadataRequest, CredentialJwtIssuerMetadataResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialJwtIssuerMetadata response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )

  }

  override def credentialIssuerJwks(
      body: CredentialIssuerJwksRequest
  ): F[CredentialIssuerJwksResponse] = {

    // s"/api/${config.apiKey}/credential/issuer/jwks")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "issuer", "jwks"))
    postRequest[CredentialIssuerJwksRequest, CredentialIssuerJwksResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialIssuerJwks response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialOfferCreate(
      body: CredentialOfferCreateRequest
  ): F[CredentialOfferCreateResponse] = {

    // s"/api/${config.apiKey}/credential/offer/create")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "offer", "create"))
    postRequest[CredentialOfferCreateRequest, CredentialOfferCreateResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialOfferCreate response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )

  }

  override def credentialOfferInfo(
      body: CredentialOfferInfoRequest
  ): F[CredentialOfferInfoResponse] = {

    // s"/api/${config.apiKey}/credential/offer/info")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "offer", "info"))
    postRequest[CredentialOfferInfoRequest, CredentialOfferInfoResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialOfferInfo response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialSingleParse(
      body: CredentialSingleParseRequest
  ): F[CredentialSingleParseResponse] = {

    // s"/api/${config.apiKey}/credential/single/parse")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "single", "parse"))
    postRequest[CredentialSingleParseRequest, CredentialSingleParseResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialSingleParse response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialSingleIssue(
      body: CredentialSingleIssueRequest
  ): F[CredentialSingleIssueResponse] = {

    // s"/api/${config.apiKey}/credential/single/issue")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "single", "issue"))
    postRequest[CredentialSingleIssueRequest, CredentialSingleIssueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialSingleIssue response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialBatchParse(
      body: CredentialBatchParseRequest
  ): F[CredentialBatchParseResponse] = {

    // "/api/${config.apiKey}/credential/batch/parse")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "batch", "parse"))
    postRequest[CredentialBatchParseRequest, CredentialBatchParseResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialBatchParse response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialBatchIssue(
      body: CredentialBatchIssueRequest
  ): F[CredentialBatchIssueResponse] = {

//"/api/${config.apiKey}/credential/batch/issue")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "batch", "issue"))
    postRequest[CredentialBatchIssueRequest, CredentialBatchIssueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialBatchIssue response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialDeferredParse(
      body: CredentialDeferredParseRequest
  ): F[CredentialDeferredParseResponse] = {

    // "/api/${config.apiKey}/credential/deferred/parse")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "deferred", "parse"))
    postRequest[CredentialDeferredParseRequest, CredentialDeferredParseResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialDeferredParse response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def credentialDeferredIssue(
      body: CredentialDeferredIssueRequest
  ): F[CredentialDeferredIssueResponse] = {

//s"/api/${config.apiKey}/credential/deferred/issue")

    val uri = baseUri.withPath(List("api", config.apiKey, "credential", "deferred", "issue"))
    postRequest[CredentialDeferredIssueRequest, CredentialDeferredIssueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"CredentialDeferredIssue response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def idTokenReissue(
      body: IDTokenReissueRequest
  ): F[IDTokenReissueResponse] = {

//s"/api/${config.apiKey}/id_token/reissue")

    val uri = baseUri.withPath(List("api", config.apiKey, "id_token", "reissue"))
    postRequest[IDTokenReissueRequest, IDTokenReissueResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"IDTokenReissue response  with action ${resp.action} ,result code ${resp
            .resultCode} and message ${resp.resultMessage}")
      )
  }

  override def authorizationTicketInfo(
      body: AuthorizationTicketInfoRequest
  ): F[AuthorizationTicketInfoResponse] = {

    // s"/api/${config.apiKey}/auth/ticket/info")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "ticket", "info"))
    postRequest[AuthorizationTicketInfoRequest, AuthorizationTicketInfoResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"AuthorizationTicketInfo response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def authorizationTicketUpdate(
      body: AuthorizationTicketUpdateRequest
  ): F[AuthorizationTicketUpdateResponse] = {

    // "/api/${config.apiKey}/auth/ticket/update")

    val uri = baseUri.withPath(List("api", config.apiKey, "auth", "ticket", "update"))
    postRequest[AuthorizationTicketUpdateRequest, AuthorizationTicketUpdateResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"AuthorizationTicketUpdate response  with action ${resp
            .action} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )
  }

  override def tokenCreateBatch(
      body: List[TokenCreateRequest],
      dryRun: Boolean
  ): F[TokenCreateBatchResponse] = {

    /// api/${config.apiKey}/auth/token/create/batch")

    val uri = baseUri
      .withPath(List("api", config.apiKey, "auth", "token", "create", "batch"))
      .addParam("dryRun", dryRun.toString)
    postRequest[List[TokenCreateRequest], TokenCreateBatchResponse](uri, body)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(s"TokenCreateBatch response  with request id ${resp
            .requestId} ,result code ${resp.resultCode} and message ${resp.resultMessage}")
      )

  }

  override def getTokenCreateBatchStatus(
      requestId: String
  ): F[TokenCreateBatchStatusResponse] = {

    // s"/api/${config.apiKey}/auth/token/create/batch/status/$requestId"

    val uri = baseUri.withPath(
      List("api", config.apiKey, "auth", "token", "create", "batch", "status", requestId)
    )
    getRequest[TokenCreateBatchStatusResponse](uri)
      .send(backend)
      .map(_.body)
      .rethrow
      .flatTap(resp =>
        logger.info(
          s"TokenCreateBatchStatus response  with result ${resp.status.map(_.result)} ,result code ${resp
              .resultCode} and message ${resp.resultMessage}"
        )
      )
  }

}

object AuthleteServiceImpl {

  def apply[F[_]: Async](
      client: Http4sBackend[F],
      config: AuthleteConfig,
      logger: Logger[F]
  ): F[AuthleteServiceImpl[F]] = Async[F].pure(new AuthleteServiceImpl[F](client, config, logger))

  def make[F[_]: Async](
      client: Http4sBackend[F],
      config: AuthleteConfig,
      logger: Logger[F]
  ): Resource[F, AuthleteServiceImpl[F]] = apply(client, config, logger).toResource

}
