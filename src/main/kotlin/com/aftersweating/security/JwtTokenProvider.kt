package com.after.sweating.security

import com.after.sweating.common.exception.CustomException
import com.after.sweating.common.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.SignatureException
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(private val userDetailService: UserDetailsServiceImpl) {
    val JWT_SECRET: String = "secret"
    val key: Key = Keys.hmacShaKeyFor(JWT_SECRET.toByteArray(StandardCharsets.UTF_8))
    val SIGNATURE_ALG: SignatureAlgorithm = SignatureAlgorithm.HS256

    fun generateAccessToken(userDetails: UserDetails): String {
        val EXP_TIME: Long = 1000L * 60 * 3
        return createToken(userDetails, EXP_TIME)
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        val EXP_TIME: Long = 1000L * 60 * 3
        return createToken(userDetails, EXP_TIME)
    }

    private fun createToken(userDetails: UserDetails, expire: Long): String {
        val claims: Claims = Jwts.claims().setSubject(userDetails.username)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expire))
            .signWith(key, SIGNATURE_ALG)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: ExpiredJwtException) {
            throw CustomException(ErrorCode.DATA_NOT_FOUND,"")
        } catch (e: SignatureException) {
            throw CustomException(ErrorCode.DATA_NOT_FOUND,"")
        } catch (e: BadCredentialsException) {
            throw CustomException(ErrorCode.DATA_NOT_FOUND,"Invalid username or password")
        }
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    fun resolveToken(request: HttpServletRequest) :String?{
        return request.getHeader("Authorization")
    }

    // JWT 토큰에서 인증 정보 조회
    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailService.loadUserByUsername(getUsernameFromToken(token))
        return UsernamePasswordAuthenticationToken(userDetails,"",userDetails.authorities)

    }

    // 토큰에서 회원 정보 추출
    private fun getUsernameFromToken(token: String) :String{
        return getClaimsFromToken(token).subject
    }

    private fun getClaimsFromToken(token: String) :Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

}