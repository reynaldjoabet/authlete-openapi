package models

import io.circe.Codec

/** @param type
  *   The type of this element. From _\"OAuth 2.0 Rich Authorization
  *   Requests\"_: _\"The type of authorization data as a string. This field MAY
  *   define which other elements are allowed in the request. This element is
  *   REQUIRED.\"_ This property is always NOT `null`.
  * @param locations
  *   The resources and/or resource servers. This property may be `null`. From
  *   _\"OAuth 2.0 Rich Authorization Requests\"_: _\"An array of strings
  *   representing the location of the resource or resource server. This is
  *   typically composed of URIs.\"_ This property may be `null`.
  * @param actions
  *   The actions. From _\"OAuth 2.0 Rich Authorization Requests\"_: _\"An array
  *   of strings representing the kinds of actions to be taken at the resource.
  *   The values of the strings are determined by the API being protected.\"_
  *   This property may be `null`.
  * @param dataTypes
  *   From _\"OAuth 2.0 Rich Authorization Requests\"_: _\"An array of strings
  *   representing the kinds of data being requested from the resource.\"_ This
  *   property may be `null`.
  * @param identifier
  *   The identifier of a specific resource. From _\"OAuth 2.0 Rich
  *   Authorization Requests\"_: _\"A string identifier indicating a specific
  *   resource available at the API.\"_ This property may be `null`.
  * @param privileges
  *   The types or levels of privilege. From \"OAuth 2.0 Rich Authorization
  *   Requests\": _\"An array of strings representing the types or levels of
  *   privilege being requested at the resource.\"_ This property may be `null`.
  * @param otherFields
  *   The RAR request in the JSON format excluding the pre-defined attributes
  *   such as `type` and `locations`. The content and semantics are specific to
  *   the deployment and the use case implemented.
  */
final case class AuthorizationDetailsElement(
    `type`: String,
    locations: Option[List[String]] = None,
    actions: Option[List[String]] = None,
    dataTypes: Option[List[String]] = None,
    identifier: Option[String] = None,
    privileges: Option[List[String]] = None,
    otherFields: Option[String] = None
) derives Codec.AsObject
