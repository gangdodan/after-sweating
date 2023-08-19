package com.after.sweating.attendance.dto

class AttendanceHistoryResponse {
    class Gathering(
        val title : String,
        val dateTime : String,
        val state : String,
        val attendanceState : String,

    ){}
    class Attendance(
        val state : String,
        val settleState : String,
    ){}

}