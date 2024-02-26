package com.sipderlab.common.domain.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PageResponse<T>(
    val totalPages: Int,
    val totalElements: Long,
    val pageNumber: Int,
    val itemSize: Int,
    val items: List<T> = mutableListOf(),
)