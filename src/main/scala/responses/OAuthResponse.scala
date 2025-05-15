package responses

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

final case class OAuthResponse(
    accessToken: String,
    refreshToken: String,
    signature: String,
    scope: String,
    idToken: String,
    instanceUrl: String,
    id: String,
    tokenType: String,
    issuedAt: String,
    additionalProperties: Map[String, String]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
