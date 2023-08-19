package com.after.sweating.comment.entity

import com.after.sweating.common.BaseTime
import com.after.sweating.gathering.entity.Gathering
//import com.momo.admin.common.domain.BaseTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Comment(
    val userId: Long,
    var content: String,
    @ManyToOne
    @JoinColumn(name = "gathering_id")
    val gathering: Gathering?,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

) : BaseTime() {

}