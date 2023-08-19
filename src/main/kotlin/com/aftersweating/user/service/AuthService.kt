//package com.after.sweating.user.service
//
//import com.after.sweating.common.exception.CustomException
//import com.after.sweating.common.exception.ErrorCode
//import com.after.sweating.security.JwtTokenProvider
//import com.after.sweating.security.UserDetailsServiceImpl
//import com.after.sweating.user.entity.User
//import com.after.sweating.user.repository.UserRepository
//import com.nimbusds.openid.connect.sdk.AuthenticationResponse
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.BadCredentialsException
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.stereotype.Service
//
//@Service
//class AuthService(
//    private val authenticationManager: AuthenticationManager,
//    private val userDetailsService: UserDetailsServiceImpl,
//    private val jwtTokenProvider: JwtTokenProvider,
//    private val userRepository: UserRepository
//) {
//    fun login(username: String, password: String): AuthenticationResponse {
//        return try {
//            val authentication = authenticationManager.authenticate(
//                UsernamePasswordAuthenticationToken(username, password)
//            )
//            SecurityContextHolder.getContext().authentication = authentication
//            val userDetails = userDetailsService.loadUserByUsername(username)
//            val accessToken = jwtTokenProvider.generateAccessToken(userDetails)
//            val refreshToken = jwtTokenProvider.generateRefreshToken(userDetails)
//
//            AuthenticationResponse(accessToken, refreshToken)
//
//        } catch (e: BadCredentialsException) {
//            throw CustomException(ErrorCode.CAN_NOT_CHANGE_SETTLEMENT_STATE,(""))
//        }
//    }
//
//    fun register(userDto : UserDto): User {
//        val newUser = User(userDto.username,userDto.password,userDto.nickname)
//        userRepository.save(newUser)
//        return newUser
//    }
//
//}