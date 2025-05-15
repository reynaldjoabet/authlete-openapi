package common

import java.time.LocalDate
import java.time.LocalDateTime

import scala.util.Try

import cats.effect.kernel.implicits
import cats.implicits.catsSyntaxEither

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.CodecMakerConfig
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import sttp.model.StatusCode

package object models {

  implicit val stringCodec: JsonValueCodec[String] = JsonCodecMaker.make(codecMakerConfig)

//implicit def arrayCodec[A: JsonValueCodec]: JsonValueCodec[Array[A]] = JsonCodecMaker.make(codecMakerConfig)

  implicit def listCodec[A: JsonValueCodec]: JsonValueCodec[List[A]] =
    JsonCodecMaker.make(codecMakerConfig)

  implicit val mapStringStringCodec: JsonValueCodec[Map[String, String]] =
    JsonCodecMaker.make(codecMakerConfig)

  implicit val localDateCodec: JsonValueCodec[LocalDate] =
    JsonCodecMaker.make(codecMakerConfig)

  implicit val localDateTimeCodec: JsonValueCodec[LocalDateTime] =
    JsonCodecMaker.make(codecMakerConfig)

  val config = CodecMakerConfig
    .withTransientEmpty(false)
    .withTransientDefault(false)
    .withTransientNone(false)
    .withDiscriminatorFieldName(None)

  // given CodecMakerConfig.PrintCodec with {}
  // CodecMakerConfig
  //     .withTransientDefault(false)
  //     .withTransientEmpty(false)

  implicit inline def codecMakerConfig: CodecMakerConfig =
    CodecMakerConfig
      .withAllowRecursiveTypes(true)
      .withDiscriminatorFieldName(None)
      .withIsStringified(true)
      // .withAdtLeafClassNameMapper(
      //   JsonCodecMaker.simpleClassName.andThen(JsonCodecMaker.enforce_snake_case)
      // )
      .withAdtLeafClassNameMapper(
        JsonCodecMaker.simpleClassName(_) // JsonCodecMaker.enforce_snake_case//.andThen(_.toUpperCase())
      )
      .withRequireDiscriminatorFirst(false)
      .withMapAsArray(true)
      .withFieldNameMapper(JsonCodecMaker.enforceCamelCase)

  val codecMakerConfig2: CodecMakerConfig =
    CodecMakerConfig
      .withMapMaxInsertNumber(65536)
      .withSetMaxInsertNumber(65536)
      .withAllowRecursiveTypes(true)
      .withDiscriminatorFieldName(None)
      .withMapAsArray(true)
    // .withFieldNameMapper {
    //   case "pos"                     => "location"
    //   case "mod"                     => "module"
    //   case s if !s.charAt(0).isUpper => JsonCodecMaker.enforce_snake_case(s)
    //   case s                         => s
    // }

  /**
    * A wrapper for JSON parsing errors.
    */
  opaque type ParsingError = String
  object ParsingError {

    def apply(throwable: Throwable): ParsingError =
      s"JSON parsing error due to $throwable"

  }

  trait JsoniterSyntaticSugar {

    extension (payload: String) {

      /**
        * Deserializes `A` from the provided JSON string.
        */
      def fromJson[A](using JsonValueCodec[A]): Either[ParsingError, A] =
        Try(readFromString[A](payload)).toEither.leftMap(ParsingError.apply)

    }

    extension [A](obj: A) {

      /**
        * Serializes `A` to a JSON string.
        */
      def toJson(using JsonValueCodec[A]): String = writeToString[A](obj)
    }

  }

  object JsoniterSyntaticSugar extends JsoniterSyntaticSugar

}
