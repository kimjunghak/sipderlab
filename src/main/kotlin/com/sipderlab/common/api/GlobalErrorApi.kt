package com.sipderlab.common.api

import com.sipderlab.common.domain.response.RestResult
import com.sipderlab.common.exception.SpiderlabException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalErrorApi {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class, SpiderlabException::class])
    fun methodArgumentNotValidException(exception: MethodArgumentNotValidException): RestResult {
        val errors = HashMap<String, String>()
        exception.fieldErrors.forEach {
            errors[it.field] = it.defaultMessage!!
        }
        return RestResult.failWithData(HttpStatus.BAD_REQUEST.value(), "Argument Not Valid", errors)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun exception(exception: Exception): RestResult {
        return RestResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    }
}