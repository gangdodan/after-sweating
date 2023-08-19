package com.after.sweating.common.exception.controller

import com.after.sweating.common.exception.CustomException
import com.after.sweating.common.exception.ErrorResponseEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler
    fun handleIllegalArgumentException(e: CustomException): ResponseEntity<ErrorResponseEntity> {
        return ErrorResponseEntity.of(e)
    }

    @ExceptionHandler
    fun handleMethodArgumentNotValidExceptions(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponseEntity> {
        return ErrorResponseEntity.of(e)
    }

}