import java.io.File
import java.nio.file.Files
import java.util.regex.Pattern
import java.util.Date
import java.util.UUID

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.JOSEObjectType
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import common.models.*
import org.http4s.Request
import org.typelevel.ci.CIStringSyntax

package object uitls {

  def withDpop[F[_]](request: Request[F], path: String, method: String): Request[F] = {
    val mDpopJwk: JWK = ???                  // "mDpopJwk" if mDpopJwk == null, then  no DPoP configuration, just pass through the original request
    val htu           = request.uri.toString // mBaseUrl + path;

    val header = new JWSHeader.Builder(JWSAlgorithm.RS256)
      .`type`(new JOSEObjectType("dpop+jwt"))
      .jwk(mDpopJwk)
      .build()

    val claims = new JWTClaimsSet.Builder()
      .claim("htm", method)
      .claim("htu", htu)
      .jwtID(UUID.randomUUID().toString())
      .issueTime(new Date())
      .build()

    val dpop = new SignedJWT(header, claims)
    dpop.sign(???) // mJwsSigner)
    request.putHeaders(org.http4s.Header.Raw(ci"DPoP", dpop.serialize()))
  }

  object BasicCredentials {

    /**
      * Regular expression to parse {@code Authorization} header.
      */
    val CHALLENGE_PATTERN = Pattern.compile("^Basic *([^ ]+) *$", Pattern.CASE_INSENSITIVE)

  }

  // BearerToken
  object BearerTokenExtractor {

    /**
      * Regular expression to parse {@code Authorization} header.
      */
    val CHALLENGE_PATTERN = Pattern.compile("^Bearer *([^ ]+) *$", Pattern.CASE_INSENSITIVE)

    /**
      * Extract a bearer token from the given {@code Authorization} header.
      *
      * @param authorization
      *   The value of {@code Authorization} header.
      *
      * @return
      *   A bearer token.
      */
    def parse(token: String) = {
      Option(token).flatMap {
        tken =>
          val matcher = CHALLENGE_PATTERN.matcher(tken)
        if matcher.matches() then Some(matcher.group(1))
        else None
      }
    }

  }

  import com.authlete.common.web.DpopToken

  object DpopTokenExtractor {

    /**
      * Regular expression to parse {@code DPoP} header.
      */
    val CHALLENGE_PATTERN = Pattern.compile("^DPoP *([^ ]+) *$", Pattern.CASE_INSENSITIVE)

    /**
      * Extract a DPoP token from the given {@code DPoP} header.
      *
      * @param dpop
      *   The value of {@code DPoP} header.
      *
      * @return
      *   A DPoP token.
      */
    def parse(dpop: String) = {
      Option(dpop).flatMap { dpop =>
        val matcher = CHALLENGE_PATTERN.matcher(dpop)
        // Return the value as is. Note that it is not Base64-encoded.
        // See https://www.ietf.org/mail-archive/web/oauth/current/msg08489.html
        if matcher.matches() then Some(matcher.group(1))
        else None
      }
    }

  }

}
