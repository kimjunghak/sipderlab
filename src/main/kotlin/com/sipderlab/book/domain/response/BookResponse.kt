package com.sipderlab.book.domain.response

import com.sipderlab.book.domain.state.BookStatus

data class BookResponse(
    val name: String,
    val isbn: String,
    val price: Int,
    val memberName: String,
    val status: BookStatus? = BookStatus.AVAILABLE,
)
