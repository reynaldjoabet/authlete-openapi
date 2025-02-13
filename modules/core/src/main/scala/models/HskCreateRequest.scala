package models

import io.circe.Codec

/** Represents a request to create an HSK (Hardware Security Key).
  *
  * @param kty
  *   The key type (EC or RSA).
  * @param use
  *   The key on the HSM. When the key use is "sig" (signature), the private key
  *   on the HSM is used to sign data, and the corresponding public key is used
  *   to verify the signature. When the key use is "enc" (encryption), the
  *   private key on the HSM is used to decrypt encrypted data which has been
  *   encrypted with the corresponding public key.
  * @param kid
  *   Key ID for the key on the HSM.
  * @param hsmName
  *   The name of the HSM. The identifier for the HSM that sits behind the
  *   Authlete server. For example, "google".
  * @param handle
  *   The handle for the key on the HSM. A handle is a base64url-encoded 256-bit
  *   random value (43 letters) which is assigned by Authlete on the call of the
  *   /api/hsk/create API.
  * @param publicKey
  *   The public key that corresponds to the key on the HSM.
  */
final case class HskCreateRequest(
    kty: Option[String] = None,
    use: Option[String] = None,
    kid: Option[String] = None,
    hsmName: Option[String] = None,
    handle: Option[String] = None,
    publicKey: Option[String] = None
) derives Codec.AsObject
