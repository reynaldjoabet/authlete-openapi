package models

import io.circe.Codec

/** Supported attachment types. This property corresponds to the
  * `attachments_supported` server metadata which was added by the third
  * implementer\'s draft of OpenID Connect for Identity Assurance 1.0.
  *
  * @param value
  */
enum AttachmentType(value: String) derives Codec.AsObject {
  case Embedded extends AttachmentType("EMBEDDED")
  case External extends AttachmentType("EXTERNAL")

}
