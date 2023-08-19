//package com.after.sweating.attendance.repository
//
//import com.after.sweating.attendance.dto.AttendanceHistoryResponse
//import com.querydsl.core.types.Projections
//import com.querydsl.jpa.impl.JPAQueryFactory
//
//class AttendanceQueryRepository(val jpaQueryFactory: JPAQueryFactory) {
//
//    fun findAttendanceHistory() : AttendanceHistoryResponse {
//        return jpaQueryFactory.select(Projections.fields(
//            AttendanceHistoryResponse()
//        ))
//    }
//
//    fun findAttendanceHistoryAll(){}
//
//
//}
