package models

import io.circe.Codec
import io.circe.*
import io.circe.syntax.*
import io.circe.parser.*

/** @param keys
  *   An array of [JWK](https://datatracker.ietf.org/doc/html/rfc7517)s.
  */
final case class ServiceJwksGetResponse(
    keys: Option[Array[Object]] // keys?: Array<object>;
) derives Decoder

object ServiceJwksGetResponse {
//  implicit val objectJsonEncoder:Encoder[Object]= Encoder[String].contramap{ obj=>
//    obj
//    ???
//  }

  // implicit val arrayJsonEncoder: Encoder[Array[Object]] = Encoder[String].contramap { arr =>
  //   arr.map(obj => Json.obj("value" -> obj.toString.asJson)) // Convert to JSON
  // }

  implicit val arrayJsonDecoder: Decoder[Array[Object]] =
    Decoder.decodeArray[Json].map { arr =>
      arr.map(_.noSpaces.asInstanceOf[Object]) // Convert JSON back to string
    }
}
