package com.sipderlab.common.domain.response

import org.springframework.http.HttpStatus

data class RestResult(
    val status: Int,
    val msg: String,
    val data: Any? = null
) {

    companion object {
        fun ok() = RestResult(HttpStatus.OK.value(), "success", null)
        fun failWithData(status: Int, msg: String, data: Any) = RestResult(status, msg, data)
    }
}