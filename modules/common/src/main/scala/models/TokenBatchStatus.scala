package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * The status of token batch.
  * @param batchKind
  *   The kind of the token batch.
  * @param requestId
  *   The ID of the token batch.
  * @param result
  *   The result of the token batch.
  * @param tokenCount
  *   The number of tokens in the batch.
  * @param errorCode
  *   The error code of the token batch.
  * @param errorDescription
  *   The error description of the token batch.
  * @param createdAt
  *   The time the token batch was created.
  * @param modifiedAt
  *   The time the token batch was last modified.
  */

final case class TokenBatchStatus(
    batchKind: TokenBatchStatus.BatchKind,
    requestId: String,
    result: TokenBatchStatus.Result,
    tokenCount: Long,
    errorCode: String,
    errorDescription: String,
    createdAt: Long,
    modifiedAt: Long
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object TokenBatchStatus {

  // Batch Kind.
  enum BatchKind(val value: Short) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {
    /*
     * The token create batch.
     */
    case CREATE extends BatchKind(1)
  }

  enum Result(val value: Short) derives ConfiguredJsonValueCodec, Schema, Codec.AsObject {

    /**
      * The token batch process has been successfully completed. All tokens have been processed as
      * expected, with no errors or interruptions.
      */
    case SUCCEEDED extends Result(1)

    /**
      * The token batch process has not completed successfully due to an issue encountered during
      * execution. This status is used when the process starts but is interrupted before completion,
      * often because of errors such as exceptions thrown during runtime. The batch process may have
      * partially completed before the failure occurred.
      */
    case FAILED extends Result(2)

  }

}
