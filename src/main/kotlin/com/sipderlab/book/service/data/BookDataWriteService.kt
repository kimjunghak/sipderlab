package com.sipderlab.book.service.data

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.state.BookStatus
import com.sipderlab.book.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class BookDataWriteService(
    private val bookRepository: BookRepository
) {

    fun createBook(book: Book): Book = bookRepository.save(book)

    //테스트 데이터 입력 전용
    fun saveAllBooks(books: List<Book>): List<Book> = bookRepository.saveAll(books)

    fun borrowBooks(bookIds: List<Long>): String? {
        val books = bookRepository.findAllByBookIdInAndStatus(bookIds, BookStatus.AVAILABLE)
        books.forEach {
            it.borrowCnt++
            it.status = BookStatus.BORROWED
            it.borrowedAt = LocalDateTime.now()
        }
        return if(books.size != bookIds.size) "Some books do not exist." else null
    }

    fun returnBooks() {
        val needReturnBooks =
            bookRepository.findAllByStatusAndBorrowedAtIsBefore(BookStatus.BORROWED, LocalDateTime.now().minusSeconds(10L))

        needReturnBooks.forEach {
            it.status = BookStatus.AVAILABLE
            it.borrowedAt = null
        }
    }
}