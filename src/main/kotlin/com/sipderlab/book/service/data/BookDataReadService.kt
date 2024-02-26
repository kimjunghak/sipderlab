package com.sipderlab.book.service.data

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.state.BookStatus
import com.sipderlab.book.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class BookDataReadService(
    private val bookRepository: BookRepository,
) {

    fun availableBookList(pageable: Pageable): Page<Book> = bookRepository.findAllByStatus(BookStatus.AVAILABLE, pageable)

}