package com.sipderlab.common.domain.response

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class RestResponse(
    val status: Int,
    val msg: String? = "An exception occurred",
    val data: Any? = null
) {

    companion object {
        fun ok(data: Any? = null) = RestResponse(HttpStatus.OK.value(), "success", data)
        fun fail(status: Int, msg: String? = "error", data: Any? = null): RestResponse = RestResponse(status, msg, data)

    }
}