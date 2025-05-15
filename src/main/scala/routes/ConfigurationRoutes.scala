package routes

import cats.effect.kernel.Async
import cats.syntax.all.*

import common.models.*
import org.http4s.*
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.`Content-Type`
import services.AuthleteService

/**
  * An implementation of an OpenID Provider configuration endpoint.
  *
  * <p> An OpenID Provider that supports <a href=
  * "https://openid.net/specs/openid-connect-discovery-1_0.html">OpenID Connect Discovery 1.0</a>
  * must provide an endpoint that returns its configuration information in a JSON format. Details
  * about the format are described in "<a
  * href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata" >3. OpenID
  * Provider Metadata</a>" in OpenID Connect Discovery 1.0. </p>
  *
  * <p> Note that the URI of an OpenID Provider configuration endpoint is defined in "<a
  * href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfigurationRequest"
  * >4.1. OpenID Provider Configuration Request</a>" in OpenID Connect Discovery 1.0. In short, the
  * URI must be: </p>
  *
  * <blockquote> Issuer Identifier + {@code /.well-known/openid-configuration} </blockquote>
  *
  * <p> <i>Issuer Identifier</i> is a URL to identify an OpenID Provider. For example,
  * {@code https://example.com}. For details about Issuer Identifier, See <b>{@code issuer}</b> in
  * "<a href="https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata" >3.
  * OpenID Provider Metadata</a>" (OpenID Connect Discovery 1.0) and <b>{@code iss}</b> in "<a
  * href="https://openid.net/specs/openid-connect-core-1_0.html#IDToken">2. ID Token</a>" (OpenID
  * Connect Core 1.0). </p>
  *
  * <p> You can change the Issuer Identifier of your service using the management console (<a
  * href="https://www.authlete.com/documents/so_console">Service Owner Console</a>). Note that the
  * default value of Issuer Identifier is not appropriate for commercial use, so you should change
  * it. </p>
  *
  * @see
  *   <a href="https://openid.net/specs/openid-connect-discovery-1_0.html" >OpenID Connect Discovery
  *   1.0</a>
  *
  * @see
  *   <a href="https://www.rfc-editor.org/rfc/rfc8414.html" >RFC 8414 OAuth 2.0 Authorization Server
  *   Metadata</a>
  */
final case class ConfigurationRoutes[F[_]: Async](authleteService: AuthleteService[F])
    extends Http4sDsl[F] {

  val routes = HttpRoutes.of[F] {

    /**
      * OpenID Provider configuration endpoint.
      *
      * <p> This implementation accepts {@code "pretty"} and {@code "patch"} as request parameters,
      * but they are not standardized ones. They are processed just to demonstrate capabilities of
      * Authlete's {@code /service/configuration} API. Note that the version of Authlete must be
      * 2.2.36 or greater to use the request parameters. </p>
      *
      * <p> The value of the {@code patch} request parameter is a <b>JSON Patch</b> that conforms to
      * <a href="https://www.rfc-editor.org/rfc/rfc6902">RFC 6902 JavaScript Object Notation (JSON)
      * Patch</a>. API callers can make the Authlete API modify JSON on Authlete side before it
      * returns the configuration JSON. Of course, API callers can modify JSON as they like AFTER
      * they receive a response from the Authlete API, so API callers do not necessarily need to use
      * the {@code patch} request parameter. </p>
      */
    case GET -> Root / ".well-known/openid-configuration" :? PrettyParam(pretty) :? PatchParam(
          patch
        ) =>

      getServiceConfiguration(pretty, patch)

    case GET -> Root / "./well-known/oauth-authorization-server" :? PrettyParam(
          pretty
        ) :? PatchParam(patch) =>
      getServiceConfiguration(pretty, patch)

  }

  object PrettyParam extends OptionalQueryParamDecoderMatcher[Boolean]("pretty")
  object PatchParam  extends OptionalQueryParamDecoderMatcher[String]("patch")

// If either or both of the 'pretty' request parameter
  // and the 'patch' request parameter are given.
  // Call the /service/configuration API with HTTP POST,
  // which is supported since Authlete 2.2.36.
  private def getServiceConfiguration(
      pretty: Option[Boolean],
      patch: Option[String]
  ): F[Response[F]] =
    (pretty, patch) match {
      case (Some(pretty), Some(patch)) =>
        val serviceConfigurationRequest = ServiceConfigurationRequest(Some(pretty), Some(patch))
        authleteService
          .getServiceConfiguration(serviceConfigurationRequest)
          .flatMap { config =>
            Ok(config, `Content-Type`(MediaType.application.json, Charset.`UTF-8`))
          }

      case (Some(pretty), None) =>
        val serviceConfigurationRequest = ServiceConfigurationRequest(Some(pretty), None)
        authleteService
          .getServiceConfiguration(serviceConfigurationRequest)
          .flatMap { config =>
            Ok(config, `Content-Type`(MediaType.application.json, Charset.`UTF-8`))
          }

      case (None, Some(patch)) =>
        val serviceConfigurationRequest = ServiceConfigurationRequest(None, Some(patch))
        authleteService
          .getServiceConfiguration(serviceConfigurationRequest)
          .flatMap { config =>
            Ok(config, `Content-Type`(MediaType.application.json, Charset.`UTF-8`))
          }

      case (None, None) =>
        // Call the /service/configuration API with HTTP GET.
        // Call Authlete's /api/service/configuration API.
        // The API returns a JSON that complies with
        // OpenID Connect Discovery 1.0.

        // Response as "application/json;charset=UTF-8" with 200 OK.

        authleteService
          .getServiceConfiguration(true)
          .flatMap { config =>
            Ok(config, `Content-Type`(MediaType.application.json, Charset.`UTF-8`))
          }
    }

}
