package common.models

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.circe.Codec
import sttp.tapir.Schema

/**
  * Response from Authlete's {@code /auth/authorization/ticket/update} API.
  *
  * <p> The API is used to update information about a ticket that has been issued from the
  * {@code /auth/authorization} API. </p>
  *
  * @param resultCode
  *   The result of the API call.
  * @param resultMessage
  *   A short message which explains the result of the API call.
  * @param action
  *   The next action that the authorization server implementation should take.
  * @param info
  *   Information about the ticket.
  */

final case class AuthorizationTicketUpdateResponse(
    resultCode: String,
    resultMessage: String,
    action: Option[AuthorizationTicketUpdateResponse.AuthorizationTicketUpdateResponseAction] =
      None,
    info: Option[AuthorizationTicketInfo] = None
) derives ConfiguredJsonValueCodec,
      Schema,
      Codec.AsObject

object AuthorizationTicketUpdateResponse {

  /**
    * The result of the {@code /auth/authorization/ticket/update} API call.
    */
  enum AuthorizationTicketUpdateResponseAction
      derives ConfiguredJsonValueCodec,
        Schema,
        Codec.AsObject {

    /**
      * Information about the ticket has been updated successfully.
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

}
