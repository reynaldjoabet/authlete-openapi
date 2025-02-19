package domain

import java.time.LocalDateTime

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

final case class Consent(
    consentId: String,
    permissions: Array[String],
    status: String,
    creationDateTime: LocalDateTime,
    expirationDateTime: LocalDateTime,
    statusUpdateDateTime: LocalDateTime,
    clientId: Long,
    refreshToken: String
)
