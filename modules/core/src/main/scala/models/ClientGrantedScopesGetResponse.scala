package models

import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._

/** @param resultCode
  *   The code representing the result of the API call.
  * @param resultMessage
  *   A short message explaining the result of the API call.
  * @param serviceApiKey
  *   The API key of the service.
  * @param clientId
  *   The client ID.
  * @param subject
  *   The subject (unique identifier) of the user who granted authorization to
  *   the client.
  * @param latestGrantedScopes
  *   The scopes granted to the client by the last authorization process.
  * @param mergedGrantedScopes
  *   The scopes granted by all past authorization processes.
  * @param modifiedAt
  *   The timestamp (in milliseconds) at which this record was modified.
  */
final case class ClientGrantedScopesGetResponse(
    resultCode: Option[String],
    resultMessage: Option[String],
    serviceApiKey: Option[Int],
    clientId: Option[Int],
    subject: Option[String],
    latestGrantedScopes: Option[List[String]],
    mergedGrantedScopes: Option[List[String]],
    modifiedAt: Option[Long]
)

object ClientGrantedScopesGetResponse {
  implicit val codec: Codec[ClientGrantedScopesGetResponse] = deriveCodec
}
