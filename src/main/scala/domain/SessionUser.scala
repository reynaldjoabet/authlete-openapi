package domain


import io.circe.Codec
import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import sttp.tapir.Schema
import common.models.Client
import java.time.LocalDate
//Emphasizes the user associated with a session.
final case class SessionUser(
    user: User,
    acrs: List[String],
    client: Client,
    authTime: LocalDate,
    ticket: String,
    claimNames: List[String],
    claimLocales: List[String],
    idTokenClaims: String,
    requestedClaimsForTx: List[String],
    // StringArray[] requestedVerifiedClaimsForTx:Array[String]
    oldIdaFormatUsed: Boolean
) derives ConfiguredJsonValueCodec,
     Schema, Codec.AsObject

//UserSession
//Emphasizes that it is a session belonging to a user or a representation of the user's session.
