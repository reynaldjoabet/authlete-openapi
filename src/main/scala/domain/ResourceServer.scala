// package domain

// import java.net.URI
// import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
// import com.github.plokhotnyuk.jsoniter_scala.macros.ConfiguredJsonValueCodec
// import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
// import common.models.*

// /**
//   * Dummy resource server entity that represents a resource server record.
//   */
// final case class ResourceServer(
//     /**
//       * The ID of the resource server.
//       */
//     id: String,

//     /**
//       * The secret of the resource server.
//       */
//     secret: String,

//     /**
//       * The URI of the resource server.
//       */
//     uri: URI,

//     /**
//       * The JWS alg algorithm for signing introspection responses.
//       */
//     introspectionSignAlg: JWSAlg,

//     /**
//       * The JWE alg algorithm for encrypting introspection responses.
//       */
//     introspectionEncryptionAlg: JWEAlg,

//     /**
//       * The JWE enc algorithm for encrypting introspection responses.
//       */
//     introspectionEncryptionEnc: JWEEnc,

//     /**
//       * The shared key for signing introspection responses.
//       */
//     sharedKeyForIntrospectionResponseSign: String,

//     /**
//       * The shared key for encrypting introspection responses.
//       */
//     sharedKeyForIntrospectionResponseEncryption: String,

//     /**
//       * The public key for signing introspection responses.
//       */
//     publicKeyForIntrospectionResponseEncryption: String
// )
