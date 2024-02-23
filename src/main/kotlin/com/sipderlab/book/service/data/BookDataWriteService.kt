package com.sipderlab.book.service.data

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookDataWriteService(
    private val bookRepository: BookRepository
) {

    fun saveBook(book: Book): Book = bookRepository.save(book)
}