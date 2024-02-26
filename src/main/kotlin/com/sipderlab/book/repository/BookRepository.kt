package com.sipderlab.book.repository

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.state.BookStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface BookRepository: JpaRepository<Book, Long> {
    fun findAllByBookIdInAndStatus(bookIds: List<Long>, status: BookStatus): List<Book>

    fun findAllByStatus(bookStatus: BookStatus, pageable: Pageable): Page<Book>

    fun findAllByStatusAndBorrowedAtIsBefore(bookStatus: BookStatus, currentTimeMinus10Seconds: LocalDateTime): List<Book>
}