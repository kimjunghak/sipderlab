package com.sipderlab.book.repository

import com.sipderlab.book.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BookRepository: JpaRepository<Book, Long> {
    fun findByNameContains(name: String): List<Book>

    fun findByIsbn(isbn: String): Optional<Book>
}