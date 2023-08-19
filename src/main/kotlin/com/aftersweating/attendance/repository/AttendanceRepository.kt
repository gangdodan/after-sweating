package com.after.sweating.attendance.repository

import com.after.sweating.attendance.entity.Attendance
import com.after.sweating.gathering.entity.Gathering
import org.springframework.data.jpa.repository.JpaRepository

interface AttendanceRepository : JpaRepository<Attendance,Long> {
}