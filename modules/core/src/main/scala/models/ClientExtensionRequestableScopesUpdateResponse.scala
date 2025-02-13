package models

import io.circe.Codec

/** @param requestableScopes
  */
final case class ClientExtensionRequestableScopesUpdateResponse(
    requestableScopes: Option[Seq[String]]
) derives Codec.AsObject
