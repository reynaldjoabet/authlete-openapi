package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * @param parameters
  *   Request parameters which comply with the introspection request defined in \"[2.1.
  *   Introspection Request](https://datatracker.ietf.org/doc/html/rfc7662#section-2.1)\" in RFC
  *   7662. The implementation of the introspection endpoint of your authorization server will
  *   receive an HTTP POST [[RFC 7231](https://datatracker.ietf.org/doc/html/rfc7231)] request with
  *   parameters in the `application/x-www-form-urlencoded` format. It is the entity body of the
  *   request that Authlete\'s `/api/auth/introspection/standard` API expects as the value of
  *   `parameters`.
  * @param withHiddenProperties
  *   Flag indicating whether to include hidden properties in the output. Authlete has a mechanism
  *   whereby to associate arbitrary key-value pairs with an access token. Each key-value pair has a
  *   hidden attribute. By default, key-value pairs whose hidden attribute is set to `true` are not
  *   embedded in the standard introspection output. If the `withHiddenProperties` request parameter
  *   is given and its value is `true`, `/api/auth/introspection/standard API includes all the
  *   associated key-value pairs into the output regardless of the value of the hidden attribute.
  */
final case class StandardIntrospectionRequest(
    parameters: String,
    withHiddenProperties: Option[String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object StandardIntrospectionRequest {
  // implicit val codec: JsonValueCodec[StandardIntrospectionRequest] =
  // JsonCodecMaker.make(codecMakerConfig)
}
