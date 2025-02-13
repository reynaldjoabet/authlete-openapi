package models

import io.circe.Codec

/** @param userCode
  *   A user code that is used for device verification. This is the code that
  *   the user has entered on the device for authentication.
  */
final case class DeviceVerificationRequest(userCode: String)
    derives Codec.AsObject
