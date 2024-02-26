package com.sipderlab.book.service

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.domain.request.BookRequest
import com.sipderlab.book.domain.response.BookResponse
import com.sipderlab.book.service.data.BookDataReadService
import com.sipderlab.book.service.data.BookDataWriteService
import com.sipderlab.common.domain.response.PageResponse
import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.service.data.MemberDataReadService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

@ExtendWith(MockitoExtension::class)
class BookApiServiceTest {

    @InjectMocks
    private lateinit var bookApiService: BookApiService

    @Mock
    private lateinit var bookDataReadService: BookDataReadService

    @Mock
    private lateinit var bookDataWriteService: BookDataWriteService

    @Mock
    private lateinit var memberDataReadService: MemberDataReadService

    @Test
    fun book_consignment_success() {
        // given
        val bookRequest = BookRequest("9791168473690", "세이노의 가르침", 1500)
        val book = bookRequest.toBook()
        val member = Member(1, "홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
        book.member = member

        given(memberDataReadService.findById(any())).willReturn(Optional.of(member))
        given(bookDataWriteService.createBook(any())).willReturn(book)

        // when
        bookApiService.consignmentBook(bookRequest, 1L)

        // then
        // no exception
        verify(bookDataWriteService, times(1)).createBook(book)
    }

    @Test
    fun getBooks_success() {
        val pageRequest = PageRequest.of(0, 20)

        val member = Member(1, "홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
        val bookList = mutableListOf(
            Book(
                1L,
                "9791168473690",
                "세이노의 가르침",
                1500,
            ),
            Book(
                2L,
                "9791168473691",
                "세이노의 가르침 2",
                1500,
            ),
            Book(
                3L,
                "9791168473692",
                "세이노의 가르침 3",
                1500,
            ),
            Book(
                4L,
                "9791168473693",
                "세이노의 가르침 4",
                1500,
            ),
            Book(
                5L,
                "9791168473694",
                "세이노의 가르침 5",
                1500,
            ),
        )
        bookList.forEach { it.member = member }

        val bookPages = PageImpl(bookList)

        given(bookDataReadService.availableBookList(pageRequest)).willReturn(bookPages)

        val books: PageResponse<BookResponse> = bookApiService.getBooks(pageRequest)
        assertEquals(5, books.itemSize)
        assertEquals(5, books.totalElements)
        assertEquals(0, books.pageNumber)
        assertEquals(5, books.items.size)
    }

    @Test
    fun getBooks_success_some_borrowed() {
        val pageRequest = PageRequest.of(0, 20)

        val member = Member(1, "홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
        val bookList = mutableListOf(
            Book(
                1L,
                "9791168473690",
                "세이노의 가르침",
                1500,
            ),
            Book(
                2L,
                "9791168473691",
                "세이노의 가르침 2",
                1500,
            ),
            Book(
                3L,
                "9791168473692",
                "세이노의 가르침 3",
                1500,
            ),
            Book(
                4L,
                "9791168473693",
                "세이노의 가르침 4",
                1500,
            ),
            Book(
                5L,
                "9791168473694",
                "세이노의 가르침 5",
                1500,
            ),
        )
        bookList.forEach { it.member = member }

        // 1,2번 책이 대여중이라는 가정
        val bookPages = PageImpl(listOf(bookList[2], bookList[3], bookList[4]))

        given(bookDataReadService.availableBookList(pageRequest)).willReturn(bookPages)

        val books: PageResponse<BookResponse> = bookApiService.getBooks(pageRequest)
        assertEquals(3, books.itemSize)
        assertEquals(3, books.totalElements)
        assertEquals(0, books.pageNumber)
        assertEquals(3, books.items.size)
    }
}