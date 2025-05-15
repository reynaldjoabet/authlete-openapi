package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /auth/authorization/ticket/info} API. <p> The API is used to get
  * information about a ticket that has been issued from the {@code /auth/authorization} API. </p>
  * @param resultCode
  *   The result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param info
  *   Information about the ticket.
  */

final case class AuthorizationTicketInfoResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[AuthorizationTicketInfoResponse.AuthorizationTicketInfoResponseAction],
    info: Option[AuthorizationTicketInfo]
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

/**
  * The result of the {@code /auth/authorization/ticket/info} API call.
  */
object AuthorizationTicketInfoResponse {

  enum AuthorizationTicketInfoResponseAction derives Schema, Codec.AsObject {

    /**
      * Information about the ticket has been obtained successfully.
      */
    case OK

    /**
      * The ticket was not found.
      */
    case NOT_FOUND

    /**
      * The API call was wrong. For example, the {@code ticket} request parameter was missing.
      */
    case CALLER_ERROR

    /**
      * An error occurred on Authlete side.
      */
    case AUTHLETE_ERROR

  }

  enum AuthorizationTicketInfoResponsErrorResponse
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    case NOT_FOUND
    case BAD_REQUEST
    case INTERNAL_SERVER_ERROR

  }

}
