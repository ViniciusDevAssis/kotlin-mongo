package com.course.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            httpCode = 400,
            message = "Invalid registration data",
            internalCode = "0001",
            errors = null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}