package com.sipderlab.common.domain.response

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class RestResult(
    val status: Int,
    val msg: String? = "An exception occurred",
    val data: Any? = null
) {

    companion object {
        fun ok() = RestResult(HttpStatus.OK.value(), "success", null)
        fun failWithData(status: Int, msg: String, data: Any) = RestResult(status, msg, data)

        fun fail(status: Int, msg: String?): RestResult = RestResult(status, msg)
    }
}