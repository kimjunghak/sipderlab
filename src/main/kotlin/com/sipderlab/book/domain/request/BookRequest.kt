package com.sipderlab.book.domain.request

import com.sipderlab.book.domain.entity.Book
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class BookRequest(
    @field:NotNull(message = "ISBN must not be null")
    @field:Pattern(regexp = "\\b\\d{10}\\b|\\b\\d{13}\\b", message = "ISBN must be 10 or 13 digits")
    val isbn: String,
    @field:NotNull(message = "Name must not be null")
    val name: String,
    @field:NotNull(message = "Price must not be null")
    val price: Int
) {
    fun toBook() = Book(
        isbn = this.isbn,
        name = this.name,
        price = this.price
    )
}