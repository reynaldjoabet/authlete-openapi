package common.models

import com.authlete.common.types.TokenStatus
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Token status.
  */
enum TokenStatus(val value: String) derives ConfiguredJsonValueCodec, Schema {

  /**
    * All valid tokens.
    */
  case VALID extends TokenStatus("VALID")

  /**
    * All invalid (expired) tokens.
    */
  case INVALID extends TokenStatus("INVALID")

  /**
    * All tokens.
    */
  case ALL extends TokenStatus("ALL")

}
