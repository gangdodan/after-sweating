package com.after.sweating.common.exception

import org.apache.poi.ss.formula.functions.T
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException

class ErrorResponseEntity(
    val status: Int,
    val code: String,
    val message: String,
    val validation: MutableMap<String, String>?
) {
    constructor(status: Int, code: String, message: String) : this(status, code, message, null)

    companion object {
        fun of(e: CustomException): ResponseEntity<ErrorResponseEntity> {
            val status = e.errorCode.httpStatus
            val message = e.message
            return ResponseEntity
                .status(status)
                .body(ErrorResponseEntity(status.value(),status.toString(),message))
        }

        fun of(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponseEntity> {
            val errors = HashMap<String,String>()
            e.allErrors
                .forEach { error -> errors.put((error as FieldError).field, error.defaultMessage)
                }
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),e.message?:"",errors))
        }
    }


}