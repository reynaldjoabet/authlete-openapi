package models

import io.circe.Codec

/** Supported subject types. This property corresponds to the
  * `subject_types_supported` server metadata which was added by the third
  * implementer\'s draft of OpenID Connect for Identity Assurance 1.0.
  *
  * @param value
  */
enum SubjectType(value: String) derives Codec.AsObject {
  case Public extends SubjectType("PUBLIC")
  case Pairwise extends SubjectType("PAIRWISE")
}
