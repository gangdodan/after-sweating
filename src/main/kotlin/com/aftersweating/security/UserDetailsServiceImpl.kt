package com.after.sweating.security

import com.after.sweating.user.entity.User
import com.after.sweating.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * MemberRepository를 DI받아 username에 대한 검증(DB조회)을 수행하고 그 결과를 UserDetails로 넘겨준다.
 * UserDetailService와 UserService를 따로 구현한 이유는 순환 참조를 막기 위해서입니다.
 * */

class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService{
    override fun loadUserByUsername(email: String): UserDetails {
        val user : User = userRepository.findByEmail(email)?: throw UsernameNotFoundException("존재하지 않는 username 입니다.")
        return UserDetailsImpl(user)
    }

}