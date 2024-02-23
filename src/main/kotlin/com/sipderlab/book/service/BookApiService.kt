package com.sipderlab.book.service

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.service.data.BookDataReadService
import com.sipderlab.book.service.data.BookDataWriteService
import com.sipderlab.common.exception.SpiderlabException
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookApiService(
    private val bookDataReadService: BookDataReadService,
    private val bookDataWriteService: BookDataWriteService,
) {

    fun bookConsignment(bookRequest: BookRequest) {
        val book = setupBook(bookRequest)
        bookDataWriteService.saveBook(book)
    }

    private fun setupBook(bookRequest: BookRequest): Book {
        val bookOpt: Optional<Book> = bookDataReadService.findByIsbn(bookRequest.isbn)

        val book: Book
        if(bookOpt.isEmpty) {
            book = bookRequest.toBook()
        } else {
            // 같은 책 중복일 경우 count 증가
            book = bookOpt.get()

            //책 검증
            checkBookInfo(bookRequest, book)
            book.countInc()
        }
        return book
    }

    private fun checkBookInfo(
        bookRequest: BookRequest,
        book: Book
    ) {
        if (bookRequest.isbn != book.isbn || bookRequest.name != book.name) {
            throw SpiderlabException("This book is inaccurate")
        }
    }


}