package com.unictive.unictiveUserApplication.utils

import com.unictive.unictiveUserApplication.domain.constant.ExpiredType
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

@Component
class JWTUtils {
    companion object {
        private const val secret = "s3cr3tk3y"
    }

    fun createJWT(id : String, subject: String) : String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)
        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder: JwtBuilder = Jwts.builder().setId(id)
            .setIssuedAt(now)
            .setSubject(subject)
            .claim("email", subject)
            .claim("idUser", id)
            .signWith(signatureAlgorithm, signingKey)

        val expMills = nowMills + 86400000L
        val exp = Date(expMills)
        builder.setExpiration(exp)

        return builder.compact()


    }

    fun decodeJWT(jwt: String): Claims {
        val claims: Claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
            .parseClaimsJws(jwt).body

        println("ID : ${claims.id}")
        println("Issuer : ${claims.issuer}")
        println("Subject : ${claims.subject}")
        return claims
    }


}