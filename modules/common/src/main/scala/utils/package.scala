// package common
// import java.io.File
// import java.nio.file.Files
// import java.util.Date
// import java.util.UUID
// import org.typelevel.ci.CIStringSyntax
// import com.github.plokhotnyuk.jsoniter_scala.core._
// import com.github.plokhotnyuk.jsoniter_scala.macros._
// import com.nimbusds.jose.jwk.JWK
// import com.nimbusds.jose.JOSEObjectType
// import com.nimbusds.jose.JWSAlgorithm
// import com.nimbusds.jose.JWSHeader
// import com.nimbusds.jwt.JWTClaimsSet
// import com.nimbusds.jwt.SignedJWT
// import common.models.*
// import org.http4s.Request
// package object utils {

//    def withDpop[F[_]](request: Request[F], path: String, method: String): Request[F] = {
//      val mDpopJwk: JWK = ???                  // "mDpopJwk" if mDpopJwk == null, then  no DPoP configuration, just pass through the original request
//      val htu           = request.uri.toString // mBaseUrl + path;

//      val header = new JWSHeader.Builder(JWSAlgorithm.RS256)
//        .`type`(new JOSEObjectType("dpop+jwt"))
//        .jwk(mDpopJwk)
//        .build()

//      val claims = new JWTClaimsSet.Builder()
//        .claim("htm", method)
//        .claim("htu", htu)
//        .jwtID(UUID.randomUUID().toString())
//        .issueTime(new Date())
//        .build()

//      val dpop = new SignedJWT(header, claims)
//      dpop.sign(???) // mJwsSigner)
//      request.putHeaders(org.http4s.Header.Raw(ci"DPoP", dpop.serialize()))
//    }

// }
