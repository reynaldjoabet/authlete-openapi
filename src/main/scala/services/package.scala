import scala.concurrent.duration.DurationInt

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import common.models.JsoniterSyntaticSugar.toJson
import sttp.client4.*
import sttp.client4.jsoniter.*
import sttp.model.MediaType
import sttp.model.Uri

package object services {

  private val baseRequest = basicRequest
    .auth
    .bearer("config.apiSecret")
    .contentType(MediaType.ApplicationJson)
    .readTimeout(10.seconds)
  // .readTimeout(config.requestTimeout)

  private val baseUri = Uri("https", "config.host", 8090) // config.port)

  private[services] def postRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicRequest
      .post(uri.withPath("api", "authorization"))
      .body(body.toJson)
      .contentType(MediaType.ApplicationJson)
      .response(asJson[R])

  private[services] def putRequest[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicRequest
      .put(uri.withPath("api", "authorization"))
      .body(body.toJson)
      .contentType(MediaType.ApplicationJson)
      .response(asJson[R])

  private[services] def postRequestForm[B: JsonValueCodec, R: JsonValueCodec](uri: Uri, body: B) =
    basicRequest
      .post(uri)
      .body(body.toJson)
      .response(asJson[R])
      .contentType(MediaType.ApplicationXWwwFormUrlencoded)

  private[services] def getRequest[R: JsonValueCodec](uri: Uri) =
    basicRequest.get(uri).response(asJson[R])

  private[services] def deleteRequest[R: JsonValueCodec](uri: Uri) =
    basicRequest.get(uri).response(asJson[R])

}
