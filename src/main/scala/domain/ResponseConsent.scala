package domain

import java.time.LocalDateTime

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

/**
  * ResponseConsent&#x2e;data
  *
  * @see
  *   <a href="https://openbanking-brasil.github.io/areadesenvolvedor/#tocS_ResponseConsent"
  *   >ResponseConsent</a>
  */
final case class ResponseConsent(
    consentId: String,
    creationDateTime: LocalDateTime,
    status: String,
    statusUpdateDateTime: LocalDateTime,
    permissions: Array[String],
    expirationDateTime: LocalDateTime,
    transactionFromDateTime: LocalDateTime,
    transactionToDateTime: LocalDateTime,
    links: Links,
    meta: Meta
)
