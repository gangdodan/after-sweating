package com.after.sweating.common.config

import com.amazonaws.HttpMethod
import com.after.sweating.security.JwtTokenProvider
import com.after.sweating.security.UserDetailsServiceImpl
import com.after.sweating.security.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsUtils

/**
 * post, delete 등 요청을 테스트해보기 위해 csrf는 disable 처리
 * signup(회원가입), signin(인증) 만 접근을 허용하고 다른 API 및 리소스는 모두 인증을 필요로 하도록 설정
 * 토큰방식 인증을 사용하므로 session은 STATELESS 로 설정
 * jwt토큰을 수행하는 JwtFilter를 만들고 UsernamePasswordAuthenticationFilter 이전에 배치하여 먼저 인증을 수행하도록 처리
 * authenticationManagerBean 로 AuthenticationManager를 주입받아서 Jwt토큰이 유효한 경우 임으로 인증되지 않은 AuthenticationToken에 대해 인증처리를 수행
 * */

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailService: UserDetailsServiceImpl
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .csrf().disable()
            .httpBasic().disable()
            .authorizeHttpRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers(HttpMethod.GET.name, "//**").permitAll()
            .antMatchers("/mypage/**").hasAnyRole("USER", "MANAGER", "ADMIN")
            .antMatchers("/auth/**").permitAll()
            .anyRequest().denyAll()
            .and()
            .exceptionHandling()
            .and()
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManagerBean(): AuthenticationManager {
        return authenticationManagerBean()
    }

}