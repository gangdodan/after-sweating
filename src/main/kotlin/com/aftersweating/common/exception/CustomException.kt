package com.after.sweating.common.exception


class CustomException(
    val errorCode : ErrorCode,
    override val message : String
) : RuntimeException() {

}