package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * This is the encryption algorithm to be used when encrypting a JWT on client or server side.
  * Depending upon the context, this refers to encryption done by the client or by the server. For
  * instance:
  *   - as `authorizationEncryptionEnc` value, it refers to the encryption algorithm used by server
  *     when creating a JARM response
  *   - as `requestEncryptionEnc` value, it refers to the expected encryption algorithm used by the
  *     client when encrypting a Request Object
  *   - as `idTokenEncryptionEnc` value, it refers to the algorithm used by the server to encrypt
  *     id_tokens
  */
enum JweEnc(val value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case A128CbcHs256 extends JweEnc("A128CBC_HS256")
  case A192CbcHs384 extends JweEnc("A192CBC_HS384")
  case A256CbcHs512 extends JweEnc("A256CBC_HS512")
  case A128Gcm      extends JweEnc("A128GCM")
  case A192Gcm      extends JweEnc("A192GCM")
  case A256Gcm      extends JweEnc("A256GCM")

}

object JweEnc {
  // implicit val codec: JsonValueCodec[JweEnc] =
  // JsonCodecMaker.make(codecMakerConfig)
}
