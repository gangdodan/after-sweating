package com.after.sweating.security

import com.after.sweating.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

/**
 * SpringSecurity는 UserDetails 객체를 통해 권한 정보를 관리하기 때문에 UserDetails 를 구현하고 추가 정보를 재정의 해야함
 * UserDetails를 구현하고 Member 엔티티를 생성자로 받아서 Member의 정보를 관리하도록 한다.
 * 여기서 Member는 UserDetailsService에서 검증(DB조회) 후에 넘어오는 객체이다.
 * */

class UserDetailsImpl(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.nickname

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}