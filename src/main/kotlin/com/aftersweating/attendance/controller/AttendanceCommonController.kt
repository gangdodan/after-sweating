package com.after.sweating.gathering.controller

import com.after.sweating.gathering.dto.GatheringRequest
import com.after.sweating.gathering.service.AttendanceCommonService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/attendances")
class AttendanceCommonController(
    private var attendanceCommonService: AttendanceCommonService
) {
    @PostMapping
    fun registerAttendance(@PathVariable("gathering_id") gatheringId: Long): Boolean {
        return attendanceCommonService.registerAttendance(gatheringId,userId)
    }

    @PatchMapping("/{gathering-id}")
    fun cancelAttendance(@PathVariable("gathering_id") gatheringId: Long): Boolean {
        return attendanceCommonService.cancelAttendance(gatheringId,userId)
    }
}