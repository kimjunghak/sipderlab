package com.sipderlab.common.api

import com.sipderlab.common.domain.response.RestResponse
import com.sipderlab.common.exception.SpiderlabException
import com.sipderlab.common.exception.UnAuthorizedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalErrorApi {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(exception: MethodArgumentNotValidException): RestResponse {
        val errors = HashMap<String, String>()
        exception.fieldErrors.forEach {
            errors[it.field] = it.defaultMessage!!
        }
        return RestResponse.fail(HttpStatus.BAD_REQUEST.value(), "Argument Not Valid", errors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [SpiderlabException::class])
    fun methodArgumentNotValidException(exception: SpiderlabException): RestResponse {
        return RestResponse.fail(HttpStatus.BAD_REQUEST.value(), exception.message)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [UnAuthorizedException::class])
    fun methodArgumentNotValidException(exception: UnAuthorizedException): RestResponse {
        return RestResponse.fail(HttpStatus.UNAUTHORIZED.value(), exception.message)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun exception(exception: Exception): RestResponse {
        return RestResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    }
}