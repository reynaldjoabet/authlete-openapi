package routes

import java.net.URLEncoder

import scala.jdk.CollectionConverters.*

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import domain.AuthorizationPage
import domain.User
import io.circe.syntax.*
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import org.typelevel.ci.CIStringSyntax
import services.*
import services.AuthleteService

//https://www.authlete.com/developers/definitive_guide/authorization_endpoint_impl/
final case class AuthorizationRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  /**
    * Authorization endpoint which supports OAuth 2&#x2E;0 and OpenID Connect.
    *
    * @see
    *   <a href="http://tools.ietf.org/html/rfc6749#section-3.1" >RFC 6749, 3.1. Authorization
    *   Endpoint</a>
    *
    * @see
    *   <a href="http://openid.net/specs/openid-connect-core-1_0.html#AuthorizationEndpoint" >OpenID
    *   Connect Core 1.0, 3.1.2. Authorization Endpoint (Authorization Code Flow)</a>
    *
    * @see
    *   <a href="http://openid.net/specs/openid-connect-core-1_0.html#ImplicitAuthorizationEndpoint"
    *   >OpenID Connect Core 1.0, 3.2.2. Authorization Endpoint (Implicit Flow)</a>
    *
    * @see
    *   <a href="http://openid.net/specs/openid-connect-core-1_0.html#HybridAuthorizationEndpoint"
    *   >OpenID Connect Core 1.0, 3.3.2. Authorization Endpoint (Hybrid Flow)</a>
    */
  val routes: HttpRoutes[F] = HttpRoutes.of[F] {

    /**
      * The authorization endpoint for {@@@@@@@@codeGET} method.
      *
      * <p> <a href="http://tools.ietf.org/html/rfc6749#section-3.1">RFC 6749, 3.1 Authorization
      * Endpoint</a> says that the authorization endpoint MUST support {@@@@@@@@codeGET} method.
      * </p>
      *
      * @see
      *   <a href="http://tools.ietf.org/html/rfc6749#section-3.1" >RFC 6749, 3.1 Authorization
      *   Endpoint</a>
      */
    case req @ GET -> Root / "authorization" =>
      val parameters = req.params

      authleteService
        .authorization(parameters)
        .flatMap { resp =>
          processAuthorizationResponse(resp)
        }

    /**
      * The authorization endpoint for {@@@@@@@@codePOST} method.
      *
      * <p> <a href="http://tools.ietf.org/html/rfc6749#section-3.1">RFC 6749, 3.1 Authorization
      * Endpoint</a> says that the authorization endpoint MAY support {@@@@@@@@codePOST} method.
      * </p>
      *
      * <p> In addition, <a href= "http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest"
      * >OpenID Connect Core 1.0, 3.1.2.1. Authentication Request</a> says that the authorization
      * endpoint MUST support {@@@@@@@@codePOST} method. </p>
      */
    case req @ POST -> Root / "authorization" =>
      req
        .as[UrlForm]
        .flatMap {
          form =>
            val parameters = form
              .values
              // .view.mapValues(_.headOption)
              .collect {
                case (key, value) if value.nonEmpty => key -> value.toList.head
              }
            // .map { case (key, value) =>
            //   s"${URLEncoder.encode(key, "UTF-8")}=${URLEncoder.encode(value, "UTF-8")}"
            // }
            // .mkString("&")

          //   val authorizationRequest: AuthorizationRequest =
          //     AuthorizationRequest(parameters)

          authleteService
            .authorization(parameters)
            .flatMap { resp =>
              processAuthorizationResponse(resp)
            }
        }

  }

  private def processAuthorizationResponse(resp: AuthorizationResponse): F[Response[F]] = {
    val content = resp.responseContent.getOrElse("")

    resp
      .action
      .fold(Status.InternalServerError()) {
        // 500 Internal Server Error
        case AuthorizationResponse.AuthorizationResponseAction.INTERNAL_SERVER_ERROR =>
          Status.InternalServerError(content)

        // 400 Bad Request
        case AuthorizationResponse.AuthorizationResponseAction.BAD_REQUEST =>
          Status.BadRequest(content)
        case AuthorizationResponse.AuthorizationResponseAction.LOCATION => Status.Found(content) // 302 Found

        // 200 OK
        /**
          * Create a response of {@code "200 OK"} with the given entity formatted in
          * {@code "text/html;charset=UTF-8"} .
          */
        case AuthorizationResponse.AuthorizationResponseAction.FORM => ???
        // Process the authorization request without user interaction.
        // The flow reaches here only when the authorization request
        // contained prompt=none.

        case AuthorizationResponse.AuthorizationResponseAction.NO_INTERACTION =>
          val authorizationIssueRequest: AuthorizationIssueRequest = AuthorizationIssueRequest(
            "ticket",
            "subject",
            None,
            None,
            None,
            List.empty,
            List.empty,
            None,
            None,
            None,
            List.empty,
            None,
            None,
            None
          )
          handleNoInteraction(authorizationIssueRequest)

        // Process the authorization request with user interaction.
        case AuthorizationResponse.AuthorizationResponseAction.INTERACTION =>
          handleInteraction(resp)
      }
  }

  /**
    * Create an {@link AuthorizationPageModel} instance using information contained in an
    * {@link AuthorizationResponse} object
    */

  /**
    * Handle the case where {@code action} parameter in a response from Authlete's
    * {@code /api/auth/authorization} API is {@code INTERACTION} .
    */
  //    private Response handleInteraction(AuthorizationResponse response)
  //    {
  //        return mSpi.generateAuthorizationPage(response);
  //    }

  private def handleInteraction(resp: AuthorizationResponse): F[Response[F]] = {

    // Get the user from the session if they exist.
    // User user = (User)session.getAttribute("user");
    val user: User               = ???
    val client                   = resp.client
    val serviceName              = resp.service.map(_.serviceName.getOrElse(""))
    val clientName               = client.map(_.clientName.getOrElse(""))
    val description              = client.map(_.description.getOrElse(""))
    val logoUri                  = client.map(_.logoUri.getOrElse(""))
    val clientUri                = client.map(_.clientUri.getOrElse(""))
    val policyUri                = client.map(_.policyUri.getOrElse(""))
    val tosUri                   = client.map(_.tosUri.getOrElse(""))
    val scopes                   = computeScopes(resp)
    val loginId                  = computeLoginId(resp)
    val loginIdReadOnly          = resp.subject // computeLoginIdReadOnly(resp)
    val authorizationDetails     = ""           // resp.authorizationDetails
    val purpose                  = resp.purpose
    val verifiedClaimsForIdToken = resp.idTokenClaims
//checking that there is a prompts property in the API response that contains a single LOGIN entry.
//If there is no prompts property, or it contains anything other than a single LOGIN entry, execution will flow to the error handler at the end of the method
    val loginPrompts = resp.prompts.filterNot(_ == Prompt.LOGIN)

    /// The Authlete API may indicate errors via the action property, and the servlet must handle them accordingly. Notice that, in these cases, responseContent holds the payload for the error response.
    val acr    = resp.acrs
    val claims = resp.claims
    // For "OpenID Connect for Identity Assurance 1.0"
    // setupIdentityAssurance(info);

    // Requested normal claims.
    val claimsForIdToken  = resp.claims
    val claimsForUserInfo = ??? // resp.claimsAtUserInfo

    val authorizationPage = AuthorizationPage(
      serviceName.getOrElse(""),
      clientName.getOrElse(""),
      description,
      logoUri,
      clientUri,
      policyUri,
      tosUri,
      scopes,
      loginId.getOrElse(""),
      loginIdReadOnly,
      user,
      authorizationDetails,
      purpose,
      verifiedClaimsForIdToken = List.empty,
      allVerifiedClaimsForIdTokenRequested = None,
      verifiedClaimsForUserInfo = List.empty,
      allVerifiedClaimsForUserInfoRequested = None,
      identityAssuranceRequired = None,
      oldIdaFormatUsed = None,
      claimsForIdToken,
      claimsForUserInfo
    )

    // generateAuthorizationPage
    ???
  }

  private def handleNoInteraction(body: AuthorizationIssueRequest): F[Response[F]] =
    authleteService
      .authorizationIssue(body)
      .flatMap { resp =>
        resp
          .action
          .fold(Status.InternalServerError()) {
            case AuthorizationIssueResponse
                  .AuthorizationIssueResponseAction
                  .INTERNAL_SERVER_ERROR =>
              Status.InternalServerError()
            case AuthorizationIssueResponse.AuthorizationIssueResponseAction.BAD_REQUEST =>
              Status.BadRequest()
            case AuthorizationIssueResponse.AuthorizationIssueResponseAction.LOCATION =>
              Status
                .Found(resp.responseContent.getOrElse(""))
                .map(
                  _.putHeaders(
                    Header.Raw(ci"Cache-Control", "no-store"),
                    Header.Raw(ci"Pragma", "no-cache")
                  )
                )

            case AuthorizationIssueResponse.AuthorizationIssueResponseAction.FORM => ???
          }
      }

    /**
      * Build the list of scopes to display.
      */

  private def computeScopes(resp: AuthorizationResponse): List[Scope] = {
    val scopes = resp.scopes

    val dynamicScopes = resp.dynamicScopes

    // If the authorization request does not contain dynamic scopes.

    if (dynamicScopes.nonEmpty)
      scopes ++ dynamicScopes.map { dScope =>
        Scope()
      }
    else scopes // No need to convert dynamic scopes to scopes, so the value of
    // the "scopes" response parameter are used without modification.

  }

  /**
    * Compute the initial value for the login ID field in the authorization page.
    */

  private def computeLoginId(resp: AuthorizationResponse) =
    resp.subject.orElse(resp.loginHint)

  /**
    * Return {@code "readonly"} if the authorization request requires that a specific subject be
    * used.
    */
  private def computeLoginIdReadOnly(resp: AuthorizationResponse): Option[String] =
    resp.subject

}
