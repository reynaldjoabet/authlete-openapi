package domain

import java.time.LocalDateTime

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker

/**
  * Meta
  *
  * @see
  *   <a href="https://openbanking-brasil.github.io/areadesenvolvedor/#tocS_Meta" >Meta</a>
  */
final case class Meta(totalRecords: Int, totalPages: Int, requestDateTime: LocalDateTime)
