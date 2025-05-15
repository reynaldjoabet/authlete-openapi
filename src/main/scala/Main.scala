import java.io.File
import java.nio.file.Files
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

object Main extends App {

  def authorizeRequest = 9

  println("Hello, World!")

}

import com.authlete.common.types.HSM
