package com.after.sweating.user.repository

import com.after.sweating.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long> {
    fun findByEmail(email: String) : User?
}