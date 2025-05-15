package domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

final case class Address(
    streetAddress: String,
    locality: String,
    region: String,
    postalCode: String,
    country: String
) derives Codec,
      Schema
