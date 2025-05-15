package routes

import scala.collection.immutable.List

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import domain.SessionUser
import domain.User
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import org.typelevel.ci.CIStringSyntax
import requests.*
import services.AuthleteService

final case class AuthorizationDecisionRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  /**
    * The endpoint that receives a request from the form in the authorization page.
    */

  /**
    * Process a request from the form in the authorization page.
    *
    * <p> NOTE: A better implementation would re-display the authorization page when the pair of
    * login ID and password is wrong, but this implementation does not do it for brevity. A much
    * better implementation would check the login credentials by Ajax. </p>
    *
    * @param request
    *   A request from the form in the authorization page.
    *
    * @return
    *   A response to the user agent. Basically, the response will trigger redirection to the
    *   client's redirect endpoint.
    */

  val routes: AuthedRoutes[SessionUser, F] = AuthedRoutes.of[SessionUser, F] {
    // form <- req.as[UrlForm]
    case req @ POST -> Root / "authorization" / "decision" as session =>
      req
        .req
        .as[UrlForm]
        .flatMap {
          form =>

            val parameters = form.values.collect { case (k, v) => (k, v.toList) }

          /**
            * The flag to indicate whether the client application has been granted permissions by
            * the user.
            */
          // If the end-user clicked the "Authorize" button, "authorized"
          // is contained in the request.
          val mClientAuthorized =
            parameters.get("authorized").map(_.nonEmpty).getOrElse(false)

          // If the end-user denied the authorization request.

          /**
            * The authenticated user.
            */
          // Look up an end-user who has the login credentials.
          val mUser: Option[User] = ???

          /**
            * The time when the user was authenticated in seconds since Unix epoch.
            */
          val mUserAuthenticatedAt: Long = 0

          /**
            * The subject (= unique identifier) of the user.
            */
          // if we use refine types, then this becomes non emtpy
          val mUserSubject: Option[String] = mUser.map(_.subject)

          /**
            * The value of the "id_token" property in the "claims" request parameter (or in the
            * "claims" property in the request object) contained in the original authorization
            * request.
            */
          // Map[String, Object]
          val mIdTokenClaims: Map[String, String] = ???

          /**
            * Requested ACRs.
            */
          val mAcrs: Array[String] = ???

          /**
            * Client associated with the request.
            */
          val mClient: Client = ???
          // stored say in the session (redis)
          val authorizationDecisionParams = AuthorizationDecisionParams(
            ticket = "ticket",
            claimNames = List("name"),
            claimLocales = List("en"),
            idTokenClaims = Some("id"),
            requestedClaimsForTx = List("name"),
            requestedVerifiedClaimsForTx = List(List("name")),
            oldIdaFormatUsed = false
          )

          addTxnToClaimNames(authorizationDecisionParams)

// Claims requested to be embedded in the ID token.

          val idTokenClaims = authorizationDecisionParams.idTokenClaims

          lazy val authorizationFailRequest: AuthorizationFailRequest = AuthorizationFailRequest(
            authorizationDecisionParams.ticket,
            AuthorizationFailRequest.AuthorizationFailRequestReason.DENIED
          )
          // parameters, user, authTime, idTokenClaims, acrs, client

          lazy val authorizationIssueRequest: AuthorizationIssueRequest =
            AuthorizationIssueRequest(
              authorizationDecisionParams.ticket,
              mUserSubject.getOrElse(""),
              Some(mUserAuthenticatedAt),
              Some(mAcrs.mkString(",")),
              ??? /*mIdTokenClaims */,
              List.empty,
              List("openid", "email"),
              None,
              None,
              None,
              List.empty,
              None,
              None,
              None
            )

          // if(true)//user gave approval
          Async[F].ifM(Async[F].delay(isClientAuthorized()))(
            processAuthorizationIssueResponse(authorizationIssueRequest),
            processAuthorizationFailResponse(authorizationFailRequest)
          )
        }
  }

  private def processAuthorizationFailResponse(body: AuthorizationFailRequest): F[Response[F]] =
    authleteService
      .authorizationFail(body)
      .flatMap { resp =>
        resp
          .action
          // None will never happen.
          .fold(Status.InternalServerError()) {
            case AuthorizationFailResponse.AuthorizationFailResponseAction.INTERNAL_SERVER_ERROR =>
              Status.InternalServerError(resp.responseContent.getOrElse(""))
            case AuthorizationFailResponse.AuthorizationFailResponseAction.BAD_REQUEST =>
              Status.BadRequest(resp.responseContent.getOrElse(""))
            case AuthorizationFailResponse.AuthorizationFailResponseAction.LOCATION =>
              Status
                .Found(resp.responseContent.getOrElse(""))
                .map(
                  _.putHeaders(
                    Header.Raw(ci"Cache-Control", "no-store"),
                    Header.Raw(ci"Pragma", "no-cache")
                  )
                )
            case AuthorizationFailResponse.AuthorizationFailResponseAction.FORM => ???
          }

      }

  private def processAuthorizationIssueResponse(body: AuthorizationIssueRequest): F[Response[F]] =
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
      * Service Provider Interface to work with {@link
      * com.authlete.jaxrs.AuthorizationDecisionHandler AuthorizationDecisionHandler}.
      *
      * <p> An implementation of this interface must be given to the constructor of {@link
      * com.authlete.jaxrs.AuthorizationDecisionHandler AuthorizationDecisionHandler} class. </p>
      *
      * @author
      *   Takahiko Kawasaki
      */

    /**
      * Get the decision on the authorization request.
      *
      * @return
      *   {@code true} if the end-user has decided to grant authorization to the client application.
      *   Otherwise, {@code false} .
      */

  private def isClientAuthorized(): Boolean = ???

  /**
    * Get the time when the end-user was authenticated.
    *
    * <p> For example, if an authorization always requires an end-user to login, the authentication
    * time is always "just now", so the implementation of this method will be like the following.
    * </p>
    *
    * <blockquote> <pre style="border: 1px solid gray; padding: 0.5em; margin: 1em;"> <span
    * style="color: gray;">&#x40;Override</span> <span style="color: purple; font-weight:
    * bold;">public long</span> getUserAuthenticatedAt() { <span style="color: purple; font-weight:
    * bold;">return</span> System.currentTimeMillis() / 1000; }</pre> </blockquote>
    *
    * <p> This method is not called when {@link #isClientAuthorized()} has returned {@code false} .
    * </p>
    *
    * @return
    *   The time when the end-user authentication occurred. The number of seconds since Unix epoch
    *   (1970-01-01). Return 0 if the time is unknown.
    */
  private def getUserAuthenticatedAt(): Long = ???

  /**
    * Get the subject (= unique identifier) of the end-user. It must consist of only ASCII letters
    * and its length must not exceed 100.
    *
    * <p> In a typical case, the subject is a primary key or another unique ID of the record that
    * represents the end-user in your user database. </p>
    *
    * <p> This method is not called when {@link #isClientAuthorized()} has returned {@code false} .
    * </p>
    *
    * @return
    *   The subject (= unique identifier) of the end-user. Returning {@code null} makes the
    *   authorization request fail.
    */
  private def getUserSubject(): String = ???

  /**
    * Get the authentication context class reference (ACR) that was satisfied when the current
    * end-user was authenticated.
    *
    * <p> The value returned by this method has an important meaning only when an authorization
    * requests {@code acr} claim as an essential claim. Practically speaking, it is unlikely to
    * happen. See "<a href="http://openid.net/specs/openid-connect-core-1_0.html#acrSemantics"
    * >5.5.1.1. Requesting the "acr" Claim</a>" in <a href=
    * "http://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect Core 1.0</a> if you are
    * interested in the details. </p>
    *
    * <p> If you don't know what ACR is, return {@code null} . </p>
    *
    * @return
    *   The authentication context class reference (ACR) that was satisfied when the current
    *   end-user was authenticated.
    */
  private def getAcr(): String = ???

  /**
    * Get the value of a claim of the user.
    *
    * <p> This method may be called multiple times. On the other hand, this method is not called
    * when {@link #isClientAuthorized()} has returned {@code false} or when
    * {@link #getUserSubject()} has returned {@code null} . </p>
    *
    * <p> If the claim name starts with two colons ({@code ::}), the claim is a predefined
    * <i>transformed claim</i>. The value of the claim will be computed by Authlete, so the exact
    * value returned by this method is not so important. If this method returns a non-null value,
    * the value of the transformed claim will be computed by Authlete and embedded in the ID token.
    * </p>
    *
    * <p> Likewise, if the claim name starts with one colon ({@code :}), the claim is a
    * <i>transformed claim</i> defined in the {@code "transformed_claims"} property in the
    * {@code claims} request parameter. If this method returns a non-null value, the value of the
    * transformed claim will be computed by Authlete and embedded in the ID token. </p>
    *
    * <p> See <a href=
    * "https://bitbucket.org/openid/ekyc-ida/src/master/openid-advanced-syntax-for-claims.md"
    * >OpenID Connect Advanced Syntax for Claims (ASC) 1.0</a> for details about transformed claims.
    * </p>
    *
    * @param claimName
    *   A claim name such as {@code name} and {@code family_name} . Standard claim names are listed
    *   in "<a href= "http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims" >5.1.
    *   Standard Claims</a>" of <a href=
    *   "http://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect Core 1.0</a>. Java
    *   constant values that represent the standard claims are listed in {@link
    *   com.authlete.common.types.StandardClaims StandardClaims} class. The value of
    *   {@code claimName} does NOT contain a language tag.
    *
    * @param languageTag
    *   A language tag such as {@code en} and {@code ja} . Implementations should take this into
    *   account whenever possible. See "<a href=
    *   "http://openid.net/specs/openid-connect-core-1_0.html#ClaimsLanguagesAndScripts" >5.2.
    *   Claims Languages and Scripts</a>" in <a href=
    *   "http://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect Core 1.0</a> for
    *   details.
    *
    * @return
    *   The claim value. {@code null} if the claim value of the claim is not available.
    */
  private def getUserClaim(claimName: String, languageTag: String): Object = ???

  /**
    * Get extra properties to associate with an access token and/or an authorization code.
    *
    * <p> This method is expected to return an array of extra properties. The following is an
    * example that returns an array containing one extra property. </p>
    *
    * <pre style="border: 1px solid gray; padding: 0.5em; margin: 1em;"> <span style="color:
    * gray;">&#x40;Override</span> <span style="color: purple; font-weight: bold;">public</span>
    * {@link Property} [] getProperties() { <span style="color: purple; font-weight:
    * bold;">return</span> <span style="color: purple; font-weight: bold;">new</span>
    * {@link Property} [] { <span style="color: purple; font-weight: bold;">new</span> {@link
    * Property#Property(String, String) Property}(<span style="color:
    * darkred;">"example_parameter"</span>, <span style="color: darkred;">"example_value"</span>) };
    * }</pre> </blockquote>
    *
    * <p> Extra properties returned from this method will appear as top-level entries in a JSON
    * response from an authorization server as shown in <a href=
    * "https://tools.ietf.org/html/rfc6749#section-5.1">5.1. Successful Response</a> in RFC 6749.
    * </p>
    *
    * <p> Keys listed below should not be used and they would be ignored on the server side even if
    * they were used. It's because they are reserved in <a
    * href="https://tools.ietf.org/html/rfc6749">RFC 6749</a> and <a
    * href="http://openid.net/specs/openid-connect-core-1_0.html" >OpenID Connect Core 1.0</a>. </p>
    *
    * <ul> <li>{@code access_token} <li>{@code token_type} <li>{@code expires_in} <li>{@code
    * refresh_token} <li>{@code scope} <li>{@code error} <li>{@code error_description} <li>{@code
    * error_uri} <li>{@code id_token} </ul>
    *
    * <p> Note that <b>there is an upper limit on the total size of extra properties</b>. On the
    * server side, the properties will be (1) converted to a multidimensional string array, (2)
    * converted to JSON, (3) encrypted by AES/CBC/PKCS5Padding, (4) encoded by base64url, and then
    * stored into the database. The length of the resultant string must not exceed 65,535 in bytes.
    * This is the upper limit, but we think it is big enough. </p>
    *
    * @return
    *   Extra properties. If {@code null} is returned, any extra property will not be associated.
    *
    * @since 1.3
    */
  private def getProperties(): Array[Property] = ???

  /**
    * Get scopes to associate with an access token and/or an authorization code.
    *
    * <p> If {@code null} is returned, the scopes specified in the original authorization request
    * from the client application are used. In other cases, including the case of an empty array,
    * the specified scopes will replace the original scopes contained in the original authorization
    * request. </p>
    *
    * <p> Even scopes that are not included in the original authorization request can be specified.
    * However, as an exception, <code>"openid"</code> scope is ignored on the server side if it is
    * not included in the original request. It is because the existence of <code>"openid"</code>
    * scope considerably changes the validation steps and because adding <code>"openid"</code>
    * triggers generation of an ID token (although the client application has not requested it) and
    * the behavior is a major violation against the specification. </p>
    *
    * <p> If you add <code>"offline_access"</code> scope although it is not included in the original
    * request, keep in mind that the specification requires explicit consent from the user for the
    * scope (<a href= "http://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess" >OpenID
    * Connect Core 1.0, 11. Offline Access</a>). When <code>"offline_access"</code> is included in
    * the original request, the current implementation of Authlete's /api/auth/authorization API
    * checks whether the request has come along with <code>prompt</code> request parameter and the
    * value includes <code>"consent"</code>. However, note that the implementation of Authlete's
    * /api/auth/authorization/issue API does not perform such checking if
    * <code>"offline_access"</code> scope is added via this <code>scopes</code> parameter. </p>
    *
    * @return
    *   Scopes to associate with an authorization code and/or an access token. If a non-null value
    *   is set, the original scopes requested by the client application are replaced.
    *
    * @since 1.4
    */
  private def getScopes(): Array[String] = ???

  /**
    * Get the value of the "sub" claim to be used in the id_token.
    *
    * <p> If doing a pairwise subject derivation, this method should check the registration of the
    * current Client to see if it has a PAIRWISE subject identifier type. If so, it returns the
    * calculated string of that subject. If not, it returns {@code null} and the value of
    * {@link #getUserSubject()} is used by the API instead. </p>
    *
    * @return
    *   The value of the "sub" claim to be used in the id_token, or {@code null} if no such subject
    *   exists.
    *
    * @since 2.22
    */
  private def getSub(): String = ???

  /**
    * Get the verified claims of the user to be embedded in the ID token.
    *
    * <p> <b>NOTE</b>: Since version 2.42, this method is called only when the {@link
    * com.authlete.jaxrs.AuthorizationDecisionHandler.Params#isOldIdaFormatUsed()
    * isOldIdaFormatUsed()} method of {@link com.authlete.jaxrs.AuthorizationDecisionHandler.Params
    * AuthorizationDecisionHandler.Params} returns true. See the description of the method for
    * details. </p>
    *
    * <p> An authorization request may contain a {@code "claims"} request parameter. The value of
    * the request parameter is JSON which conforms to the format defined in <a href=
    * "https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter" >5.5. Requesting
    * Claims using the "claims" Request Parameter</a> of <a
    * href="https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect Core 1.0</a>. The
    * JSON may contain an {@code "id_token"} property. The value of the property is a JSON object
    * which lists claims that the client application wants to be embedded in the ID token. The
    * following is an example shown in the section. </p>
    *
    * <pre> { "userinfo": { "given_name": {"essential": true}, "nickname": null, "email":
    * {"essential": true}, "email_verified": {"essential": true}, "picture": null,
    * "http://example.info/claims/groups": null }, "id_token": { "auth_time": {"essential": true},
    * "acr": {"values": ["urn:mace:incommon:iap:silver"] } } } </pre>
    *
    * <p> <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html" >OpenID
    * Connect for Identity Assurance 1.0</a> has extended this mechanism to allow client
    * applications to request <b>verified claims</b>. To request verified claims, a
    * {@code "verified_claims"} property is included in the {@code "id_token"} property like below.
    * </p>
    *
    * <pre> { "id_token": { "verified_claims": { "verification": { "trust_framework": { "value":
    * "de_aml" }, "evidence": [ { "type": { "value": "id_document" }, "method": { "value": "pipp" },
    * "document": { "type": { "values": [ "idcard", "passport" ] } } } ] }, "claims": {
    * "given_name": null, "family_name": { "essential": true }, "birthdate": { "purpose": "To send
    * you best wishes on your birthday" } } } } } </pre>
    *
    * <p> This method should return the requested verified claims. </p>
    *
    * @param subject
    *   The subject of the user. The same value returned by {@link #getUserSubject()} .
    *
    * @param constraint
    *   An object that represents the {@code "verified_claims"} in the {@code "id_token"} property.
    *
    * @return
    *   The verified claims. The returned value is embedded in the ID token as the value of the
    *   {@code "verified_claims"} claim. If this method returns null, the {@code "verified_claims"}
    *   claim does not appear in the ID token.
    *
    * @since 2.25
    *
    * @see
    *   #getVerifiedClaims(String, Object)
    *
    * @deprecated
    */

  // List<VerifiedClaims> getVerifiedClaims(String subject, VerifiedClaimsConstraint constraint);

  /**
    * Get the verified claims of the user to be embedded in the ID token.
    *
    * <p> An authorization request may contain a {@code "claims"} request parameter. The value of
    * the request parameter is JSON which conforms to the format defined in <a href=
    * "https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter" >5.5. Requesting
    * Claims using the "claims" Request Parameter</a> of <a
    * href="https://openid.net/specs/openid-connect-core-1_0.html">OpenID Connect Core 1.0</a>. The
    * JSON may contain an {@code "id_token"} property. The value of the property is a JSON object
    * which lists claims that the client application wants to be embedded in the ID token. The
    * following is an example shown in the section. </p>
    *
    * <pre> { "userinfo": { "given_name": {"essential": true}, "nickname": null, "email":
    * {"essential": true}, "email_verified": {"essential": true}, "picture": null,
    * "http://example.info/claims/groups": null }, "id_token": { "auth_time": {"essential": true},
    * "acr": {"values": ["urn:mace:incommon:iap:silver"] } } } </pre>
    *
    * <p> <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html" >OpenID
    * Connect for Identity Assurance 1.0</a> has extended this mechanism to allow client
    * applications to request <b>verified claims</b>. To request verified claims, a
    * {@code "verified_claims"} property is included in the {@code "id_token"} property like below.
    * </p>
    *
    * <pre> { "id_token": { "verified_claims": { "verification": { "trust_framework": null, "time":
    * null, "evidence": [ { "type": { "value": "document" }, "method": null, "document_details": {
    * "type": null } } ] }, "claims": { "given_name": null, "family_name": null, "birthdate": null }
    * } } } </pre>
    *
    * <p> The second argument ({@code verifiedClaimsRequest}) of this method represents the value of
    * {@code "verified_claims"} in the request. It is a {@code Map} instance or a {@code List}
    * instance. The latter case happens when the value of {@code "verified_claims"} is a JSON array
    * like below. </p>
    *
    * <pre> { "id_token": { "verified_claims": [ { "verification": { "trust_framework": { "value":
    * "gold" }, "evidence": [ { "type": { "value": "document" } } ] }, "claims": { "given_name":
    * null, "family_name": null } }, { "verification": { "trust_framework": { "values": [ "silver",
    * "bronze" ] }, "evidence": [ { "type": { "value": "vouch" } } ] }, "claims": { "given_name":
    * null, "family_name": null } } ] } } </pre>
    *
    * <p> When the given argument is a {@code Map} instance, it can be cast by
    * {@code Map<String, Object>} . On the other hand, in the case of a {@code List} instance, each
    * element in the list can be cast by {@code Map<String, Object>} . </p>
    *
    * <p> This method should return a {@code Map} instance or a {@code List} instance which
    * represents the value of {@code "verified_claims"} . The value will be embedded in the ID token
    * as the value of {@code "verified_claims"} . </p>
    *
    * <p> For example, to make an ID token include {@code "verified_claims"} like below, </p>
    *
    * <pre> "verified_claims": { "verification": { "trust_framework": "trust_framework_example" },
    * "claims": { "given_name": "Max", "family_name": "Meier" } } </pre>
    *
    * this method should build a {@code Map} instance like below and return it.
    *
    * <pre> Map&lt;String, Object&gt; verification = new HashMap&lt;&gt;();
    * verification.put("trust_framework", "trust_framework_example");
    *
    * Map&lt;String, Object&gt; claims = new HashMap&lt;&gt;(); claims.put("given_name", "Max");
    * claims.put("family_name", "Meier");
    *
    * Map&lt;String, Object&gt; verified_claims = new HashMap&lt;&gt;();
    * verified_claims.put("verification", verification); verified_claims.put("claims", claims);
    *
    * return verified_claims; </pre>
    *
    * @param subject
    *   The subject of the user. The same value returned by {@link #getUserSubject()} .
    *
    * @param verifiedClaimsRequest
    *   An object that represents the {@code "verified_claims"} in the {@code "id_token"} property
    *   in the {@code "claims"} request parameter. Either a {@code Map} instance or a {@code List}
    *   instance.
    *
    * @return
    *   The verified claims. The returned value is embedded in the ID token as the value of
    *   {@code "verified_claims"} . If this method returns null, {@code "verified_claims"} does not
    *   appear in the ID token.
    *
    * @since 2.42
    *
    * @see
    *   <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html" >OpenID
    *   Connect for Identity Assurance 1.0</a>
    */

  private def getVerifiedClaims(subject: String, verifiedClaimsRequest: Object): Object = ???

  def addTxnToClaimNames(params: AuthorizationDecisionParams): Unit = {
    // txn claim is always required by ConnectID Australia
    // https://cdn.connectid.com.au/specifications/digitalid-identity-assurance-profile-06.html
    val claimNames = params.claimNames
    // None             // if no claims were requested it can't be a connectid au request
    Option.when(claimNames.nonEmpty) {
      // txn will now be returned for any requests that request oidc claims - as our AS is multipurpose there's no
      // real good way to identify the ecosystem variant being tested and returning an random uuid is harmless

      Some(claimNames :+ "txn")
    }
  }

}
