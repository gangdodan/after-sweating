package com.after.sweating.security.filter

import com.after.sweating.security.JwtTokenProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * 요청이 들어올 때마다 필터가 가로채서
 * JwtTokenProvider를 이용해 헤더에서 JWT를 받아와 유효한 토큰인지 확인하고, 유효할 경우 인증정보(유저 정보)를 SecurityContextHolder에 저장하는 JwtAuthenticationFilter를 생성
 * */

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token : String? = jwtTokenProvider.resolveToken(request as HttpServletRequest)
        if (token != null && jwtTokenProvider.validateToken(token)) {
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request,response)
    }
}