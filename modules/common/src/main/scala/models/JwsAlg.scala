package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The signature algorithm for JWT. This value is represented on 'alg' attribute of the header of
  * JWT. Its semantics depend upon where it's defined, such as:
  *   - as service accessTokenSignAlg value, it defines that access tokens are JWT and the algorithm
  *     used to sign it.
  *   - as client authorizationSignAlg value, it represents the signature algorithm used when
  *     creating a JARM response.
  *   - as client requestSignAlg value, it specifies the expected signature used by the client on a
  *     Request Object.
  */
enum JwsAlg(val value: String) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

  case None   extends JwsAlg("NONE")
  case Hs256  extends JwsAlg("HS256")
  case Hs384  extends JwsAlg("HS384")
  case Hs512  extends JwsAlg("HS512")
  case Rs256  extends JwsAlg("RS256")
  case Rs384  extends JwsAlg("RS384")
  case Rs512  extends JwsAlg("RS512")
  case Es256  extends JwsAlg("ES256")
  case Es384  extends JwsAlg("ES384")
  case Es512  extends JwsAlg("ES512")
  case Ps256  extends JwsAlg("PS256")
  case Ps384  extends JwsAlg("PS384")
  case Ps512  extends JwsAlg("PS512")
  case Es256K extends JwsAlg("ES256K")
  case EdDsa  extends JwsAlg("EdDSA")

}

object JwsAlg {
  // implicit val codec: JsonValueCodec[JwsAlg] =
  // JsonCodecMaker.make(codecMakerConfig)
}
