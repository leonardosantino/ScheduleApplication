package com.application.exception.handler

import com.application.exception.BadRequestException
import com.application.exception.ForbiddenException
import com.application.exception.NotFoundException
import com.application.exception.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException) = ResponseEntity(ExceptionResponse(ex.message), NOT_FOUND)

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException) =
        ResponseEntity(
            ExceptionResponse(ex.message),
            BAD_REQUEST,
        )

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(ex: ForbiddenException) = ResponseEntity(ExceptionResponse(ex.message), FORBIDDEN)

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException) =
        ResponseEntity(
            ExceptionResponse(ex.message),
            HttpStatus.UNAUTHORIZED,
        )
}
