package requests

import java.net.URI

import com.authlete.common.types.JWEAlg
import com.authlete.common.types.JWEEnc
import com.authlete.common.types.JWSAlg
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import common.models.*
import io.circe.Codec
import sttp.tapir.Schema

final case class IntrospectionRequestParam(
    parameters: Map[String, Array[String]],
    withHiddenProperties: Boolean,
    httpAcceptHeader: String,
    rsUri: URI,
    introspectionSignAlg: JWSAlg,
    introspectionEncryptionAlg: JWEAlg,
    introspectionEncryptionEnc: JWEEnc,
    sharedKeyForSign: String,
    sharedKeyForEncryption: String,
    publicKeyForEncryption: String
)
