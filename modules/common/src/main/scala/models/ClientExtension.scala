package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param requestableScopes
  *   The set of scopes that the client application is allowed to request. This paramter will be one
  *   of the following. - `null` - an empty set - a set with at least one element When the value of
  *   this parameter is `null`, it means that the set of scopes that the client application is
  *   allowed to request is the set of the scopes that the service supports. When the value of this
  *   parameter is an empty set, it means that the client application is not allowed to request any
  *   scopes. When the value of this parameter is a set with at least one element, it means that the
  *   set is the set of scopes that the client application is allowed to request.
  * @param requestableScopesEnabled
  *   The flag to indicate whether \"Requestable Scopes per Client\" is enabled or not. If `true`,
  *   you can define the set of scopes which this client application can request. If `false`, this
  *   client application can request any scope which is supported by the authorization server.
  * @param accessTokenDuration
  *   The value of the duration of access tokens per client in seconds. In normal cases, the value
  *   of the service\'s `accessTokenDuration` property is used as the duration of access tokens
  *   issued by the service. However, if this `accessTokenDuration` property holds a non-zero
  *   positive number and its value is less than the duration configured by the service, the value
  *   is used as the duration of access tokens issued to the client application. Note that the
  *   duration of access tokens can be controlled by the scope attribute `access_token.duration`,
  *   too. Authlete chooses the minimum value among the candidates.
  * @param refreshTokenDuration
  *   The value of the duration of refresh tokens per client in seconds. In normal cases, the value
  *   of the service\'s `refreshTokenDuration` property is used as the duration of refresh tokens
  *   issued by the service. However, if this `refreshTokenDuration` property holds a non-zero
  *   positive number and its value is less than the duration configured by the service, the value
  *   is used as the duration of refresh tokens issued to the client application. Note that the
  *   duration of refresh tokens can be controlled by the scope attribute `refresh_token.duration`,
  *   too. Authlete chooses the minimum value among the candidates.
  * @param tokenExchangePermitted
  *   Get the flag indicating whether the client is explicitly given a permission to make token
  *   exchange requests ([RFC 8693][https://www.rfc-editor.org/rfc/rfc8693.html])
  */
final case class ClientExtension(
    requestableScopes: Option[List[String]],
    requestableScopesEnabled: Option[Boolean],
    accessTokenDuration: Option[Long],
    refreshTokenDuration: Option[Long],
    tokenExchangePermitted: Option[Boolean]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object ClientExtension {
  // implicit val codec: JsonValueCodec[ClientExtension] =
  // JsonCodecMaker.make(codecMakerConfig)
  def empty = ClientExtension(None, None, None, None, None)
}
