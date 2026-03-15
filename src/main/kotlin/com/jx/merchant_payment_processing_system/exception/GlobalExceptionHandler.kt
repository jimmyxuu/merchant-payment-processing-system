package com.jx.merchant_payment_processing_system.exception

import com.jx.merchant_payment_processing_system.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    // 1. Handle Validation Errors (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = "Validation failed",
            path = request.getDescription(false).removePrefix("uri="),
            validationErrors = errors
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    // 2. Handle Business Logic Errors (e.g., User already exists)
    @ExceptionHandler(RuntimeException::class)
    fun handleBusinessErrors(ex: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.CONFLICT.value(), // 409 Conflict
            error = "Conflict",
            message = ex.message,
            path = request.getDescription(false).removePrefix("uri=")
        )
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    // 3. Catch-all for everything else (500 Internal Server Error)
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "Internal Server Error",
            message = "An unexpected error occurred",
            path = request.getDescription(false).removePrefix("uri=")
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}