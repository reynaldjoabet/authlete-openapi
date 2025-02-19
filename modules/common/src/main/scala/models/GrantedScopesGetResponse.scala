package common.models

import com.authlete.common.dto.GrantedScopesGetResponse
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's <code>/client/granted_scopes/get/{clientId}</code> API.
  *
  * @param resultCode
  * @param resultMessage
  * @param serviceApiKey
  * @param clientId
  * @param subject
  * @param latestGrantedScopes
  * @param mergedGrantedScopes
  * @param modifiedAt
  */

final case class GrantedScopesGetResponse(
    resultCode: String,
    resultMessage: String,
    serviceApiKey: Option[Long],
    clientId: Option[Long],
    subject: Option[String],
    latestGrantedScopes: Option[Array[String]],
    mergedGrantedScopes: Option[Array[String]],
    modifiedAt: Option[Long]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject
