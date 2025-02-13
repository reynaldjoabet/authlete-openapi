package models

import io.circe.Codec

/** this is the 'alg' header value for encrypted JWT tokens. Depending upon the
  * context, this refers to key transport scheme to be used by the client and by
  * the server. For instance:
  *   - as `authorizationEncryptionAlg` value, it refers to the encoding
  *     algorithm used by server for transporting the keys on JARM objects
  *   - as `requestEncryptionAlg` value, it refers to the expected key transport
  *     encoding algorithm that server expects from the client when encrypting a
  *     Request Object
  *   - as `idTokenEncryptionAlg` value, it refers to the algorithm used by the
  *     server to key transport of id_tokens
  * **Please note that some of the algorithms are more secure than others, some
  * are not supported very well cross-platforms and some (like RSA1_5) are known
  * to be weak**.
  */
enum JweAlg(val value: String) derives Codec.AsObject {
  case Rsa15 extends JweAlg("RSA1_5")
  case RsaOaep extends JweAlg("RSA_OAEP")
  case RsaOaep256 extends JweAlg("RSA_OAEP_256")
  case A128Kw extends JweAlg("A128KW")
  case A192Kw extends JweAlg("A192KW")
  case A256Kw extends JweAlg("A256KW")
  case Dir extends JweAlg("DIR")
  case EcdhEs extends JweAlg("ECDH_ES")
  case EcdhEsA128Kw extends JweAlg("ECDH_ES_A128KW")
  case EcdhEsA192Kw extends JweAlg("ECDH_ES_A192KW")
  case EcdhEsA256Kw extends JweAlg("ECDH_ES_A256KW")
  case A128Gcmkw extends JweAlg("A128GCMKW")
  case A192Gcmkw extends JweAlg("A192GCMKW")
  case A256Gcmkw extends JweAlg("A256GCMKW")
  case Pbes2Hs256A128Kw extends JweAlg("PBES2_HS256_A128KW")
  case Pbes2Hs384A192Kw extends JweAlg("PBES2_HS384_A192KW")
  case Pbes2Hs512A256Kw extends JweAlg("PBES2_HS512_A256KW")
}
