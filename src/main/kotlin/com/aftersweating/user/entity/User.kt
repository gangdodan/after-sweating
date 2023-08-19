package com.after.sweating.user.entity

import com.after.sweating.common.BaseTime
import javax.persistence.*

@Entity
class User(
    email: String,
    password: String,
    nickname: String
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var email = email
        protected set

    @Column(nullable = false)
    var password = password
        protected set

    @Column(nullable = false, unique = true)
    var nickname = nickname
        protected set

    @Enumerated(EnumType.STRING)
    var level  = Level.YELLOW

}



