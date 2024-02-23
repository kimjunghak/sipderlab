package com.sipderlab.book.domain.request

import com.sipderlab.book.domain.entity.Book
import jakarta.validation.constraints.Pattern

data class BookRequest(
    @field:Pattern(regexp = "\\b\\d{10}\\b|\\b\\d{13}\\b", message = "ISBN must be 10 or 13 digits")
    val isbn: String,
    val name: String,
    val price: Int
) {
    fun toBook() = Book(
        isbn = this.isbn,
        name = this.name,
        price = this.price
    )
}