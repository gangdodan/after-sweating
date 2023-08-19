package com.after.sweating.gathering.service

import com.after.sweating.attendance.entity.Attendance
import com.after.sweating.attendance.enums.AttendanceState
import com.after.sweating.attendance.repository.AttendanceRepository
import com.after.sweating.common.exception.CustomException
import com.after.sweating.common.exception.ErrorCode
import com.after.sweating.gathering.entity.Address
import com.after.sweating.gathering.entity.DateTime
import com.after.sweating.gathering.entity.Gathering
import com.after.sweating.gathering.repository.GatheringRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@Service
@Transactional
class AttendanceCommonService(
    val attendanceRepository: AttendanceRepository,
    val gatheringRepository: GatheringRepository
) {
    fun registerAttendance(gatheringId: Long, userId: Long): Boolean {
        val gathering = gatheringRepository.findByIdOrNull(gatheringId)
        return gathering?.let {
            attendanceRepository.save(
                Attendance(
                    attendanceId = userId,
                    gathering = it
                )
            )
            true
        } ?: run {
            throw CustomException(ErrorCode.DATA_NOT_FOUND, "신청 가능한 게더링이 없습니다.")
            false
        }
    }

    fun cancelAttendance(gatheringId: Long): Boolean {
        var attendance = attendanceRepository.findByIdOrNull(gatheringId)
        if (attendance == null) throw CustomException(ErrorCode.DATA_NOT_FOUND, "취소할 신청 내역이 없습니다.")
        attendance.state = AttendanceState.CANCELED

        return true
    }

    fun validate(){}
}
