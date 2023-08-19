package com.after.sweating.gathering.entity

import com.after.sweating.attendance.entity.Attendance
import com.after.sweating.comment.entity.Comment
import com.after.sweating.common.BaseTime
import com.after.sweating.gathering.enums.GatheringState
import lombok.Builder
import javax.persistence.*

@Entity
@Builder
class Gathering(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var content: String,
    var capacity: Int,
    @Embedded
    var location: Address,
    @Embedded
    var datetime: DateTime

) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    var state: GatheringState = GatheringState.OPEN
    var settle: Long = 0

    @OneToMany(mappedBy = "gathering", fetch = FetchType.LAZY)
    val comments: MutableList<Comment> = mutableListOf()

    @OneToMany(mappedBy = "gathering", fetch = FetchType.LAZY)
    val attendance: MutableList<Attendance> = mutableListOf()

    fun updateGathering(gathering : Gathering) : Long{
        this.title = gathering.title
        this.content = gathering.content
        this.location = gathering.location
        this.datetime = gathering.datetime
        return this.id
    }

}