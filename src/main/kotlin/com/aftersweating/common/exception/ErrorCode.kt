package com.after.sweating.common.exception


import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
){

    UNABLE_TO_PROCESS(HttpStatus.SERVICE_UNAVAILABLE, "현재 해당 요청을 수행할 수 없습니다, 관리자에게 문의하세요."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "조회하려는 데이터가 존재하지 않습니다."),
    REQUEST_CONFLICT(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    REQUEST_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    CAN_NOT_CHANGE_SETTLEMENT_STATE(HttpStatus.CONFLICT, "정산의 상태를 변경할 수 없습니다.")

}
