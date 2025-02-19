package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Information about a credential request.
  *
  * <p> This class represents information about a credential request that is sent to the credential
  * endpoint or information about a credential request in the {@code credential_requests} array in a
  * batch credential request that is sent to the batch credential endpoint. </p>
  *
  * @param identifier
  *   The identifier of the credential request.
  * @param format
  *   The value of the {@code format} parameter in the credential request.
  * @param bindingKey
  *   The binding key specified by the proof in the credential request.
  * @param bindingKeys
  *   The binding keys specified by the proofs in the credential request.
  * @param details
  *   The details about the credential request.
  *
  * @see
  *   <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html" >OpenID
  *   for Verifiable Credential Issuance</a>
  */
final case class CredentialRequestInfo(
    identifier: String,
    format: String,
    bindingKey: String,
    bindingKeys: Option[List[String]],
    details: String
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
