package com.sipderlab.book.service

import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.domain.response.BookResponse
import com.sipderlab.book.service.data.BookDataReadService
import com.sipderlab.book.service.data.BookDataWriteService
import com.sipderlab.common.domain.response.PageResponse
import com.sipderlab.common.exception.SpiderlabException
import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.service.data.MemberDataReadService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookApiService(
    private val bookDataReadService: BookDataReadService,
    private val bookDataWriteService: BookDataWriteService,
    private val memberDataReadService: MemberDataReadService,
) {

    fun consignmentBook(bookRequest: BookRequest, memberId: Long) {
        val newBook = bookRequest.toBook()
        // isbn 검증?
        val consignmentMember = getMember(memberId)
        newBook.member = consignmentMember

        bookDataWriteService.createBook(newBook)
    }

    private fun getMember(memberId: Long): Member {
        val consignmentMember =
            memberDataReadService.findById(memberId).orElseThrow { SpiderlabException("Member does not exist.") }
        return consignmentMember
    }

    fun getBooks(pageable: Pageable): PageResponse<BookResponse> {
        val books = bookDataReadService.availableBookList(pageable)
        val contents: List<BookResponse> = books.content.map { BookResponse(it.name, it.isbn, it.price, it.member.name) }
        return PageResponse(books.totalPages, books.totalElements, books.number, books.size, contents)
    }

    fun borrowBooks(bookIds: List<Long>): String? {
        // 동시성 문제 방지
        synchronized(this) {
            return bookDataWriteService.borrowBooks(bookIds)
        }
    }
}