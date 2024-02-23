package com.sipderlab.book.service.data

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class BookDataReadService(
    private val bookRepository: BookRepository,
) {

    fun findByIsbn(isbn: String): Optional<Book> = bookRepository.findByIsbn(isbn)
}