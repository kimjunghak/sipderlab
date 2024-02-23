package com.sipderlab.book.service

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.service.data.BookDataReadService
import com.sipderlab.book.service.data.BookDataWriteService
import com.sipderlab.common.exception.SpiderlabException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*

@ExtendWith(MockitoExtension::class)
class BookApiServiceTest {

    @InjectMocks
    private lateinit var bookApiService: BookApiService

    @Mock
    private lateinit var bookDataReadService: BookDataReadService

    @Mock
    private lateinit var bookDataWriteService: BookDataWriteService

    @Test
    fun book_consignment_success() {
        // given
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val book = bookRequest.toBook()

        given(bookDataReadService.findByIsbn(bookRequest.isbn)).willReturn(Optional.empty())

        // when
        bookApiService.bookConsignment(bookRequest)

        // then
        // no exception
        verify(bookDataWriteService, times(1)).saveBook(any())
        assertEquals(1, book.count)
    }

    @Test
    fun book_consignment_success_same() {
        // given
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val book = bookRequest.toBook()

        given(bookDataReadService.findByIsbn(bookRequest.isbn)).willReturn(Optional.of(book))

        // when
        bookApiService.bookConsignment(bookRequest)

        // then
        // no exception
        verify(bookDataWriteService, times(1)).saveBook(any())
        assertEquals(2, book.count)
    }

    @Test
    fun book_consignment_fail_not_match() {
        // given
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val book = Book(1L, "9791168473690", "세이노의 가르침 3편", 1500)

        given(bookDataReadService.findByIsbn(bookRequest.isbn)).willReturn(Optional.of(book))

        // when
        val throws = assertThrows<SpiderlabException> { bookApiService.bookConsignment(bookRequest) }

        // then
        assertEquals("This book is inaccurate", throws.message)
    }
}