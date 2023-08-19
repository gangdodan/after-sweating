package com.after.sweating.attendance.entity

import com.after.sweating.attendance.enums.AttendanceState
import com.after.sweating.gathering.entity.Gathering
import javax.persistence.*

@Entity
class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val attendanceId : Long,
    var state : AttendanceState = AttendanceState.DONE,
    @ManyToOne
    @JoinColumn(name = "gathering_id")
    val gathering : Gathering
) {

}